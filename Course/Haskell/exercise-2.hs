-- 4.
gap :: (Char, Char) -> Int -> String -> Int
gap (a,b) n []  = 0
gap (a,b) n (x:xs)
    | (a == x && last(take (n + 1) xs) == b)     = 1 + gap (a,b) n xs 
    | otherwise     = gap (a,b) n xs

-- 5. Given two strings s1 and s2, calculates their distance using the following formula 
-- ( (count of how many of the characters in s1 do not appear in s2 + (count of how many of the characters in s2 do not appear in s1) ) / ( (length of s1) + (length of s2) ).
-- If both of the lists are empty, then the distance is 0.
distance1 :: String -> String -> Float
distance1 "" "" = 0
distance1 s1 s2 = 
    let inS2 = length [c | c <- s1, c `elem` s2]
        inS1 = length [c | c <- s2, c `elem` s1]
    in (fromIntegral ((length s1 - inS2) + (length s2 - inS1))) / (fromIntegral (length s1 + length s2))

-- 6. 
clusters :: (String -> String -> Float) -> Float -> [String] -> [[String]]
clusters f d ss =
    [[s2|s2 <- ss, (f s1 s2) <= d]|s1 <- ss]