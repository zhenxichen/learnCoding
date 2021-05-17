/**
* 用于对方法进行测试的类
*/
#define _CRT_SECURE_NO_WARNINGS

#include <stdio.h>
#include <stdlib.h>
#include "transfer.h"

int main() {
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
	// grayToBinaryByThreshold(bf, bi, palettes, imgData, 60);
	imgData = grayToBinaryByDither(bf, bi, palettes, imgData, 4);
	bf = getBinaryFileHeaderByDither(bf, bi, 4);
	bi = getBinaryInfoHeaderByDither(bi, 4);
	lineBytes = (DWORD)WIDTHBYTES(bi.biWidth * bi.biBitCount);
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
	return 0;
}