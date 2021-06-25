package main

import "fmt"
import "os"

func main() {
	fmt.Print(os.Args[0])
	s := ""
	sep := " "
	for _, arg := range os.Args[1:] {		// 生成的_是索引，arg是参数的值
		s += sep + arg
	}
	fmt.Print(s)
}