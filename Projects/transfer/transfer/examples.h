﻿#ifndef _EXAMPLES_H_
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

#endif // !_EXAMPLES_H_
