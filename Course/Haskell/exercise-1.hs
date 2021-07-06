-- 1. Given a list of strings and a character, evaluates to a list with all the strings of the input list that either begin or end with the input character.
headOrLast :: [String] -> Char -> [String]
headOrLast s c = [str | str <- s, head str == c || last str == c]

-- 2. Evaluates the given credits.
credits :: (Char, Int) -> (Char, Int) -> Int
credits (c1, i1) (c2, i2)
  | (c1 == 's' && i1 == 14) || (c2 == 's' && i2 == 14)    = 6
  | i1 == i2    = 4
  | c1 == c2    = 2
  | otherwise  = 0

-- 3. Produces a list with all elements of the input list such that the element is followed by a greater number in the input list (the next number is greater)
nextIsGreater :: [Int] -> [Int]
nextIsGreater arr
  | length arr <= 1   = []
  | head arr < head (tail arr)    = [head arr] ++ nextIsGreater (tail arr)
  | otherwise         = nextIsGreater (tail arr)

