#ifndef _TRANSFER_H_
#define _TRANSFER_H_

#include <Windows.h>
#include <math.h>

#define WIDTHBYTES(i) ((i+31)/32*4)
#define BINARYWIDTH(i) ((i+3)/4*4)

/// <summary>
/// 通过单阈值法将图片从灰度图转为二值图
/// </summary>
/// <param name="bitMapFileHeader">灰度图的文件头</param>
/// <param name="bitMapInfoHeader">灰度图的信息头</param>
/// <param name="palettes">灰度图的调色板</param>
/// <param name="imgData">灰度图的图片数据</param>
/// <param name="threshold">单阈值法的阈值</param>
/// <returns>二值图的图片数据</returns>
unsigned char** grayToBinaryByThreshold(BITMAPFILEHEADER bitMapFileHeader,
	BITMAPINFOHEADER bitMapInfoHeader, RGBQUAD* palettes, unsigned char** imgData, char threshold);

/// <summary>
/// 通过Dither算法将灰度图转为二值图
/// 注:
/// 当前函数只负责生成二值图的图片数据
/// 需要另外调用getBinaryInfoHeaderByDither()和getBinaryFileHeaderByDither()函数生成信息头和文件头
/// </summary>
/// <param name="bitMapFileHeader">灰度图的文件头</param>
/// <param name="bitMapInfoHeader">灰度图的信息头</param>
/// <param name="palettes">灰度图的调色板</param>
/// <param name="imgData">灰度图的图片数据</param>
/// <param name="matrixSize">Dither矩阵的尺寸n</param>
/// <returns></returns>
unsigned char** grayToBinaryByDither(BITMAPFILEHEADER bitMapFileHeader,
	BITMAPINFOHEADER bitMapInfoHeader, RGBQUAD* palettes, unsigned char** imgData, int matrixSize);

/// <summary>
/// 通过Ordered Dither算法将灰度图转为二值图
/// </summary>
/// <param name="bitMapFileHeader">灰度图的文件头</param>
/// <param name="bitMapInfoHeader">灰度图的信息头</param>
/// <param name="palettes">灰度图的调色板</param>
/// <param name="imgData">灰度图的图片数据</param>
/// <param name="matrixSize">Dither矩阵的尺寸n</param>
/// <returns></returns>
unsigned char** grayToBinaryByOrderedDither(BITMAPFILEHEADER bitMapFileHeader,
	BITMAPINFOHEADER bitMapInfoHeader, RGBQUAD* palettes, unsigned char** imgData, int matrixSize);

/// <summary>
/// 生成n*n的Dither矩阵
/// </summary>
/// <param name="n">矩阵的尺寸（必须为2的整数倍）</param>
/// <returns>Dither矩阵</returns>
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

/// <summary>
/// 生成二值图的信息头（Dither）
/// </summary>
/// <param name="bi">灰度图的信息头</param>
/// <param name="n">Dither矩阵的大小为n*n</param>
/// <returns>Dither对应的二值图的信息头</returns>
BITMAPINFOHEADER getBinaryInfoHeaderByDither(BITMAPINFOHEADER bi, int n);

/// <summary>
/// 生成二值图的文件头（Dither）
/// </summary>
/// <param name="bf">灰度图的文件头</param>
/// <param name="n">Dither矩阵的大小</param>
/// <returns>Dither对应的二值图的文件头</returns>
BITMAPFILEHEADER getBinaryFileHeaderByDither(BITMAPFILEHEADER bf, BITMAPINFOHEADER bi, int n);

#endif // !_TRANSFER_H_
