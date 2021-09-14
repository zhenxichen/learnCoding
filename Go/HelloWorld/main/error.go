package main

import "fmt"

// 常量命名遵循UpperCamelCase
const Threshold = 10

// 当error作为函数的返回值，且函数有多个返回值时
// error应该作为最后一个返回值
func FuncWithError(value int) (int, error) {
	if value > Threshold {
		return value, fmt.Errorf("out of threshold")
	}
	return value, nil
}

func main() {
	input, err := FuncWithError(12)
	if err != nil {
		fmt.Errorf("input %d is error", input)
		return
	}
	fmt.Printf("input %d is correct", input)
}