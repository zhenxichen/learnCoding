#ifndef _TRANSFER_H_
#define _TRANSFER_H_

#include <Windows.h>
#include <math.h>

#define WIDTHBYTES(i) ((i+31)/32*4)
#define BINARYWIDTH(i) ((i+3)/4*4)

/**
* 通过单阈值法将图片从灰度图转为二值图
*/
unsigned char** grayToBinaryByThreshold(BITMAPFILEHEADER bitMapFileHeader,
	BITMAPINFOHEADER bitMapInfoHeader, RGBQUAD* palettes, unsigned char** imgData, char threshold);

/**
* 通过Dither算法将灰度图转为二值图
*/
unsigned char** grayToBinaryByDither(BITMAPFILEHEADER bitMapFileHeader,
	BITMAPINFOHEADER bitMapInfoHeader, RGBQUAD* palettes, unsigned char** imgData, int matrixSize);

/**
* 生成Dither矩阵
*/
unsigned char** getDitherMatrix(int n);

/**
* 生成二值图的信息头（单阈值法）
*/
BITMAPINFOHEADER getBinaryInfoHeaderByThreshold(BITMAPINFOHEADER bi);

/**
* 生成二值图的文件头（单阈值法）
*/
BITMAPFILEHEADER getBinaryFileHeaderByThreshold(BITMAPFILEHEADER bf);

/**
* 生成二值图的调色盘
*/
RGBQUAD* getBinaryRGBQuad();

#endif // !_TRANSFER_H_
