#ifndef _EXAMPLES_H_
#define _EXAMPLES_H_

#include <stdio.h>
#include <stdlib.h>
#include "transfer.h"

/// <summary>
/// 通过单阈值法将灰度图转为二值图的示例
/// </summary>
void g2bThresholdExample();

/// <summary>
/// 通过Dither算法将灰度图转为二值图的示例
/// </summary>
void g2bDitherExample();

/// <summary>
/// 通过Ordered Dither算法将灰度图转为二值图的示例
/// </summary>
void g2bOrderedDitherExample();

/// <summary>
/// 通过RGB-HSI的方法计算亮度分量，并由此将24位真彩色图转为8位灰度图
/// </summary>
void c2gHSIExample();

/// <summary>
/// 通过RGB-YCbCr的方法计算亮度分量，并由此将24位真彩色图转为8位灰度图
/// </summary>
void c2gYCbCrExample();

#endif // !_EXAMPLES_H_

