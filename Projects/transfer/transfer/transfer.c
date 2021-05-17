#include "transfer.h"

unsigned char** grayToBinaryByThreshold(BITMAPFILEHEADER bitMapFileHeader,
	BITMAPINFOHEADER bitMapInfoHeader, RGBQUAD* palettes, unsigned char** imgData, char threshold) {
	LONG width = bitMapInfoHeader.biWidth;		// 宽度
	LONG height = bitMapInfoHeader.biHeight;	// 高度
	LONG count = bitMapInfoHeader.biBitCount;	// 位深
	DWORD lineBytes = (DWORD)WIDTHBYTES(width * count);		// 每行的字节数
	// 由于读取文件时已经倒序读入，这里只需要顺序读取即可
	for (int i = 0; i < height; i++) {
		for (int j = 0; j < lineBytes; j++) {
			char index = imgData[i][j];		// 获取颜色对应的调色板索引
			RGBQUAD color = palettes[index];		// 根据索引获取对应的调色板
			BYTE gray = color.rgbRed;		// 灰度图的RGB数值相等，因此只需取其中一个
			if (gray >= threshold) {
				imgData[i][j] = 255;
			}
			else {
				imgData[i][j] = 0;
			}
		}
	}
	return imgData;
}

unsigned char** grayToBinaryByDither(BITMAPFILEHEADER bitMapFileHeader,
	BITMAPINFOHEADER bitMapInfoHeader, RGBQUAD* palettes, unsigned char** imgData, int matrixSize) {
	LONG width = bitMapInfoHeader.biWidth;		// 宽度
	LONG height = bitMapInfoHeader.biHeight;	// 高度
	LONG count = bitMapInfoHeader.biBitCount;	// 位深
	DWORD lineBytes = (DWORD)WIDTHBYTES(width * count);		// 每行的字节数
	// 将图片数据范围进行压缩
	for (int i = 0; i < height; i++) {
		for (int j = 0; j < lineBytes; j++) {
			imgData[i][j] = imgData[i][j] * (matrixSize * matrixSize + 1) / 256;
		}
	}
	unsigned char** ditherMatrix = getDitherMatrix(matrixSize);		// 计算dither矩阵
	// allocate memory for output matrix
	int outputSize = matrixSize * height;
	unsigned char** outputData = (unsigned char**)malloc(outputSize * sizeof(unsigned char*));
	for (int i = 0; i < outputSize; i++) {
		outputData[i] = (unsigned char*)malloc(outputSize * sizeof(unsigned char));
	}
	// 利用dither矩阵进行二值化
	for (int i = 0; i < height; i++) {
		for (int j = 0; j < width; j++) {
			int x = i * matrixSize;
			int y = j * matrixSize;
			for (int ditherI = 0; ditherI < matrixSize; ditherI++) {
				for (int ditherJ = 0; ditherJ < matrixSize; ditherJ++) {
					if (imgData[i][j] >= ditherMatrix[ditherI][ditherJ]) {
						outputData[x + ditherI][y + ditherJ] = 255;
					}
					else {
						outputData[x + ditherI][y + ditherJ] = 0;
					}
				}
			}
		}
	}
	free(imgData);
	return outputData;
}

unsigned char** grayToBinaryByOrderedDither(BITMAPFILEHEADER bitMapFileHeader,
	BITMAPINFOHEADER bitMapInfoHeader, RGBQUAD* palettes, unsigned char** imgData, int matrixSize) {
	LONG width = bitMapInfoHeader.biWidth;		// 宽度
	LONG height = bitMapInfoHeader.biHeight;	// 高度
	LONG count = bitMapInfoHeader.biBitCount;	// 位深
	DWORD lineBytes = (DWORD)WIDTHBYTES(width * count);		// 每行的字节数
	// 将图片数据范围进行压缩
	for (int i = 0; i < height; i++) {
		for (int j = 0; j < lineBytes; j++) {
			imgData[i][j] = imgData[i][j] * (matrixSize * matrixSize + 1) / 256;
		}
	}
	unsigned char** ditherMatrix = getDitherMatrix(matrixSize);		// 计算dither矩阵
	// 在图像矩阵中滑动Dither矩阵
	for (int i = 0; i < width; i++) {
		int x = i % matrixSize;
		for (int j = 0; j < height; j++) {
			int y = j % matrixSize;
			if (imgData[i][j] > ditherMatrix[x][y]) {
				imgData[i][j] = 255;
			}
			else {
				imgData[i][j] = 0;
			}
		}
	}
	return imgData;
}

unsigned char** getDitherMatrix(int n) {
	// allocate memory
	char** matrix = (char**)malloc(n * sizeof(char*));
	for (int i = 0; i < n; i++) {
		matrix[i] = (char*)malloc(n * sizeof(char));
	}
	// 递归基
	if (n == 2) {
		matrix[0][0] = 0;
		matrix[0][1] = 2;
		matrix[1][0] = 1;
		matrix[1][1] = 3;
		return matrix;
	}
	// 递归调用，获取上一级Dither矩阵
	char** originMatrix = getDitherMatrix(n / 2);
	// 通过D(n/2)计算当前Dither矩阵
	//	4D(n/2)				4D(n/2) + 2U(n/2) 
	//  4D(n/2) + 3U(n/2)	4D(n/2) + U(n/2)
	for (int i = 0; i < n / 2; i++) {
		for (int j = 0; j < n / 2; j++) {
			matrix[i][j] = 4 * originMatrix[i][j];		// 左上: 4D(n/2)
			matrix[n / 2 + i][j] = 4 * originMatrix[i][j] + 3;	// 左下: 4D(n/2) + 3U(n/2)
			matrix[i][n / 2 + j] = 4 * originMatrix[i][j] + 2;	// 右上: 4D(n/2) + 2U(n/2)
			matrix[n / 2 + i][n / 2 + j] = 4 * originMatrix[i][j] + 1;	// 右下: 4D(n/2) + U(n/2)
		}
	}
	// free D(N/2)'s memory, avoid memory leak.
	free(originMatrix);
	return matrix;
}

BITMAPINFOHEADER getBinaryInfoHeaderByThreshold(BITMAPINFOHEADER bi) {
	bi.biBitCount = 1;
	return bi;
}

BITMAPFILEHEADER getBinaryFileHeaderByThreshold(BITMAPFILEHEADER bf) {
	bf.bfSize -= (254 * sizeof(RGBQUAD));
	bf.bfOffBits -= (254 * sizeof(RGBQUAD));
	return bf;
}

RGBQUAD* getBinaryRGBQuad() {
	RGBQUAD* rgbQuad = (RGBQUAD*)malloc(2 * sizeof(RGBQUAD));
	RGBQUAD white;
	white.rgbRed = 0; white.rgbGreen = 0; white.rgbBlue = 0; white.rgbReserved = 0;
	RGBQUAD black;
	black.rgbRed = 255; black.rgbGreen = 255; black.rgbBlue = 255; black.rgbReserved = 0;
	rgbQuad[0] = white;
	rgbQuad[1] = black;
	return rgbQuad;
}

BITMAPINFOHEADER getBinaryInfoHeaderByDither(BITMAPINFOHEADER bi, int n) {
	bi.biHeight = n * bi.biHeight;
	bi.biWidth = n * bi.biWidth;
	bi.biSizeImage = bi.biHeight * bi.biWidth;
	return bi;
}

BITMAPFILEHEADER getBinaryFileHeaderByDither(BITMAPFILEHEADER bf, BITMAPINFOHEADER bi, int n) {
	DWORD grayImageDataSize = bi.biSizeImage;		// 灰度图的图片数据大小
	bf.bfSize -= grayImageDataSize;
	DWORD binaryImageDataSize = bi.biSizeImage * n * n;		// 二值图的图片数据大小
	bf.bfSize += binaryImageDataSize;
	return bf;
}