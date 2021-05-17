// examples.c
// 提供各函数的调用示例
#define _CRT_SECURE_NO_WARNINGS

#include "examples.h"

void g2bThresholdExample() {
	BITMAPFILEHEADER bf;
	BITMAPINFOHEADER bi;
	RGBQUAD* palettes = (RGBQUAD*)malloc(256 * sizeof(RGBQUAD));
	FILE* fpFrom, * fpTarget;
	char** imgData = NULL;
	fpFrom = fopen("resources/lenna_gray.bmp", "rb");
	fread(&bf, sizeof(BITMAPFILEHEADER), 1, fpFrom);		// 读取文件头
	fread(&bi, sizeof(BITMAPINFOHEADER), 1, fpFrom);		// 读取信息头
	fread(palettes, sizeof(RGBQUAD), 256, fpFrom);			// 读取调色板
	DWORD lineBytes = (DWORD)WIDTHBYTES(bi.biWidth * bi.biBitCount);	// 计算每行的字节数
	// 为存储图片数据的指针分配内存空间
	imgData = (char**)malloc(bi.biHeight * sizeof(char*));
	for (int i = 0; i < bi.biHeight; i++) {
		imgData[i] = (char*)malloc(lineBytes * sizeof(char));
	}
	// 读取图片数据
	for (int i = bi.biHeight - 1; i >= 0; i--) {
		fread(imgData[i], lineBytes, 1, fpFrom);
	}
	fclose(fpFrom);
	// 函数调用示例范围
	grayToBinaryByThreshold(bf, bi, palettes, imgData, 60);
	// 函数调用示例范围
	// 写入结果
	fpTarget = fopen("res/lenna_bin.bmp", "wb");
	fwrite(&bf, sizeof(BITMAPFILEHEADER), 1, fpTarget);
	fwrite(&bi, sizeof(BITMAPINFOHEADER), 1, fpTarget);
	fwrite(palettes, sizeof(RGBQUAD), 256, fpTarget);
	for (int i = bi.biHeight - 1; i >= 0; i--) {
		fwrite(imgData[i], lineBytes, 1, fpTarget);
	}
	fclose(fpTarget);
	free(palettes);
	free(imgData);
}

void g2bDitherExample() {
	BITMAPFILEHEADER bf;
	BITMAPINFOHEADER bi;
	RGBQUAD* palettes = (RGBQUAD*)malloc(256 * sizeof(RGBQUAD));
	FILE* fpFrom, * fpTarget;
	char** imgData = NULL;
	fpFrom = fopen("resources/lenna_gray.bmp", "rb");
	fread(&bf, sizeof(BITMAPFILEHEADER), 1, fpFrom);		// 读取文件头
	fread(&bi, sizeof(BITMAPINFOHEADER), 1, fpFrom);		// 读取信息头
	fread(palettes, sizeof(RGBQUAD), 256, fpFrom);
	DWORD lineBytes = (DWORD)WIDTHBYTES(bi.biWidth * bi.biBitCount);
	imgData = (char**)malloc(bi.biHeight * sizeof(char*));
	for (int i = 0; i < bi.biHeight; i++) {
		imgData[i] = (char*)malloc(lineBytes * sizeof(char));
	}
	for (int i = bi.biHeight - 1; i >= 0; i--) {
		fread(imgData[i], lineBytes, 1, fpFrom);
	}
	fclose(fpFrom);
	// 函数调用示例范围
	imgData = grayToBinaryByDither(bf, bi, palettes, imgData, 4);
	bf = getBinaryFileHeaderByDither(bf, bi, 4);
	bi = getBinaryInfoHeaderByDither(bi, 4);
	lineBytes = (DWORD)WIDTHBYTES(bi.biWidth * bi.biBitCount);
	// 函数调用示例范围
	// 写入结果
	fpTarget = fopen("res/lenna_bin.bmp", "wb");
	fwrite(&bf, sizeof(BITMAPFILEHEADER), 1, fpTarget);
	fwrite(&bi, sizeof(BITMAPINFOHEADER), 1, fpTarget);
	fwrite(palettes, sizeof(RGBQUAD), 256, fpTarget);
	for (int i = bi.biHeight - 1; i >= 0; i--) {
		fwrite(imgData[i], lineBytes, 1, fpTarget);
	}
	fclose(fpTarget);
	free(palettes);
	free(imgData);
}

void g2bOrderedDitherExample() {
	BITMAPFILEHEADER bf;
	BITMAPINFOHEADER bi;
	RGBQUAD* palettes = (RGBQUAD*)malloc(256 * sizeof(RGBQUAD));
	FILE* fpFrom, * fpTarget;
	char** imgData = NULL;
	fpFrom = fopen("resources/lenna_gray.bmp", "rb");
	fread(&bf, sizeof(BITMAPFILEHEADER), 1, fpFrom);		// 读取文件头
	fread(&bi, sizeof(BITMAPINFOHEADER), 1, fpFrom);		// 读取信息头
	fread(palettes, sizeof(RGBQUAD), 256, fpFrom);
	DWORD lineBytes = (DWORD)WIDTHBYTES(bi.biWidth * bi.biBitCount);
	imgData = (char**)malloc(bi.biHeight * sizeof(char*));
	for (int i = 0; i < bi.biHeight; i++) {
		imgData[i] = (char*)malloc(lineBytes * sizeof(char));
	}
	for (int i = bi.biHeight - 1; i >= 0; i--) {
		fread(imgData[i], lineBytes, 1, fpFrom);
	}
	fclose(fpFrom);
	// 函数调用示例范围
	grayToBinaryByOrderedDither(bf, bi, palettes, imgData, 4);
	// 函数调用示例范围
	// 写入结果
	fpTarget = fopen("res/lenna_bin.bmp", "wb");
	fwrite(&bf, sizeof(BITMAPFILEHEADER), 1, fpTarget);
	fwrite(&bi, sizeof(BITMAPINFOHEADER), 1, fpTarget);
	fwrite(palettes, sizeof(RGBQUAD), 256, fpTarget);
	for (int i = bi.biHeight - 1; i >= 0; i--) {
		fwrite(imgData[i], lineBytes, 1, fpTarget);
	}
	fclose(fpTarget);
	free(palettes);
	free(imgData);
}

void c2gHSIExample() {
	BITMAPFILEHEADER bf;
	BITMAPINFOHEADER bi;
	unsigned char*** imgData = NULL;
	FILE* fpFrom, * fpTarget;
	fpFrom = fopen("resources/lenna.bmp", "rb");		// 打开一张24位真彩色图片
	fread(&bf, sizeof(BITMAPFILEHEADER), 1, fpFrom);	// 读取文件头
	fread(&bi, sizeof(BITMAPINFOHEADER), 1, fpFrom);	// 读取信息头
	// 为存储图片数据的指针分配内存空间
	imgData = (unsigned char***)malloc(bi.biHeight * sizeof(unsigned char**));
	for (int i = 0; i < bi.biHeight; i++) {
		imgData[i] = (unsigned char**)malloc(bi.biWidth * sizeof(unsigned char*));
		for (int j = 0; j < bi.biWidth; j++) {
			imgData[i][j] = (unsigned char*)malloc(3 * sizeof(unsigned char));		// 每个像素分配三个字节空间存储RGB
		}
	}
	// 从图片文件中读取图片数据
	for (int i = bi.biHeight - 1; i >= 0; i--) {
		for (int j = 0; j < bi.biWidth; j++) {
			fread(imgData[i][j], 3, 1, fpFrom);			// 读取当前像素的RGB值
		}
	}
	fclose(fpFrom);
	// 函数调用示例范围
	char** outputData = NULL;
	outputData = colorToGrayByRGBtoHSI(bf, bi, imgData);
	bf = getGrayFileHeader(bf, bi);
	bi = getGrayInfoHeader(bi);
	RGBQUAD* rgbQuads = getGrayPalettes();
	// 函数调用示例范围
	DWORD lineBytes = (DWORD)WIDTHBYTES(bi.biWidth * bi.biBitCount);
	// 写入文件
	fpTarget = fopen("res/lenna_gray.bmp", "wb");
	fwrite(&bf, sizeof(BITMAPFILEHEADER), 1, fpTarget);
	fwrite(&bi, sizeof(BITMAPINFOHEADER), 1, fpTarget);
	fwrite(rgbQuads, sizeof(RGBQUAD), 256, fpTarget);
	for (int i = bi.biHeight - 1; i >= 0; i--) {
		fwrite(outputData[i], lineBytes, 1, fpTarget);
	}
	fclose(fpTarget);
	free(imgData);
	free(rgbQuads);
	free(outputData);
}

void c2gYCbCrExample() {
	BITMAPFILEHEADER bf;
	BITMAPINFOHEADER bi;
	unsigned char*** imgData = NULL;
	FILE* fpFrom, * fpTarget;
	fpFrom = fopen("resources/lenna.bmp", "rb");		// 打开一张24位真彩色图片
	fread(&bf, sizeof(BITMAPFILEHEADER), 1, fpFrom);	// 读取文件头
	fread(&bi, sizeof(BITMAPINFOHEADER), 1, fpFrom);	// 读取信息头
	// 为存储图片数据的指针分配内存空间
	imgData = (unsigned char***)malloc(bi.biHeight * sizeof(unsigned char**));
	for (int i = 0; i < bi.biHeight; i++) {
		imgData[i] = (unsigned char**)malloc(bi.biWidth * sizeof(unsigned char*));
		for (int j = 0; j < bi.biWidth; j++) {
			imgData[i][j] = (unsigned char*)malloc(3 * sizeof(unsigned char));		// 每个像素分配三个字节空间存储RGB
		}
	}
	// 从图片文件中读取图片数据
	for (int i = bi.biHeight - 1; i >= 0; i--) {
		for (int j = 0; j < bi.biWidth; j++) {
			fread(imgData[i][j], 3, 1, fpFrom);			// 读取当前像素的RGB值
		}
	}
	fclose(fpFrom);
	// 函数调用示例范围
	char** outputData = NULL;
	outputData = colorToGrayByRGBtoYCbCr(bf, bi, imgData);
	bf = getGrayFileHeader(bf, bi);
	bi = getGrayInfoHeader(bi);
	RGBQUAD* rgbQuads = getGrayPalettes();
	// 函数调用示例范围
	DWORD lineBytes = (DWORD)WIDTHBYTES(bi.biWidth * bi.biBitCount);
	// 写入文件
	fpTarget = fopen("res/lenna_gray.bmp", "wb");
	fwrite(&bf, sizeof(BITMAPFILEHEADER), 1, fpTarget);
	fwrite(&bi, sizeof(BITMAPINFOHEADER), 1, fpTarget);
	fwrite(rgbQuads, sizeof(RGBQUAD), 256, fpTarget);
	for (int i = bi.biHeight - 1; i >= 0; i--) {
		fwrite(outputData[i], lineBytes, 1, fpTarget);
	}
	fclose(fpTarget);
	free(imgData);
	free(rgbQuads);
	free(outputData);
}