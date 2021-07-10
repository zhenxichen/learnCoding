-- 10. Make a program that reads repeatedly lines from terminal
-- (that is a complete program you need to compile and that includes the main function). 
-- If the line is follows the format of one of the three lines below:
-- Int ‘+’ Int
-- Int ‘-‘ Int
-- Int ‘*’ Int
-- then calculate the result of the arithmetic operation. 
-- Otherwise output an error message: “I cannot calculate that”
-- Stop when the user types “quit” and respond to that with "bye"

import Text.Read

split :: Eq a => a -> [a] -> [[a]]
split d [] = []
split d s = x : split d (drop 1 y) where (x,y) = span (/= d) s

add :: Maybe Integer -> Maybe Integer -> Integer
add Nothing _           = error "There is Nothing in calcu."
add _ Nothing           = error "There is Nothing in calcu."
add (Just a) (Just b)   = a + b

sub :: Maybe Integer -> Maybe Integer -> Integer
sub Nothing _           = error "There is Nothing in calcu."
sub _ Nothing           = error "There is Nothing in calcu."
sub (Just a) (Just b)   = a - b

mul :: Maybe Integer -> Maybe Integer -> Integer
mul Nothing _           = error "There is Nothing in calcu."
mul _ Nothing           = error "There is Nothing in calcu."
mul (Just a) (Just b)   = a * b

main :: IO()
main = do
    line <- getLine
    if line == "quit"
        then do 
            putStrLn "bye"
        else do
            let opers = split ' ' line
            if length opers /= 3
                then do
                    putStrLn "I cannot calculate that"
                    main
                else do
                    let num1 = readMaybe (head opers) :: Maybe Integer
                    let num2 = readMaybe (last opers) :: Maybe Integer
                    let operator = head (tail opers)
                    if num1 == Nothing || num2 == Nothing
                        then do
                            putStrLn "I cannot calculate that"
                            main
                        else do
                            case operator of
                                "+" -> putStrLn (show (add num1 num2))
                                "-" -> putStrLn (show (sub num1 num2))
                                "*" -> putStrLn (show (mul num1 num2))
                                otherwise ->putStrLn "I cannot calculate that"
                            main