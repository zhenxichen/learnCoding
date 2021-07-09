-- 1. given an integer, returns an integer telling how many times digit 1 appears in the integer value
-- a) by converting the integer into a string
countDigitOneByString :: Int -> Int
countDigitOneByString i =
    let str = show i
    in length [s | s <- str, s == '1']

-- b) by utilizing the numeric values only
countDigitOne :: Int -> Int
countDigitOne 0 = 0
countDigitOne x
    | (x `mod` 10) == 1       = 1 + countDigitOne (x `div` 10)
    | otherwise         = countDigitOne (x `div` 10)

-- 2.  Make a function that, given two lists of items of type Maybe [Char], returns a list of items of 
-- type Char as follows. The items of the result list are such they have appeared in both input lists 
-- inside some Just [Char] structure.
appearedInBoth :: [Maybe [Char]] -> [Maybe [Char]] -> Maybe [Char]
appearedInBoth [] _         = Nothing
appearedInBoth _ []         = Nothing
appearedInBoth s1 s2        = 
    let charInS1 = concat [toValue s | s <- s1]
        charInS2 = concat [toValue s | s <- s2]
        ret = removeRepeat [s | s <- charInS1, s `elem` charInS2]
    in if ret == "" then Nothing else (Just ret)

toValue :: (Maybe [Char]) -> [Char]
toValue Nothing     = ""
toValue (Just s)      = s

removeRepeat :: String -> String
removeRepeat ""         = ""
removeRepeat (x : xs)
    | x `elem` xs       = removeRepeat (xs)
    | otherwise         = [x] ++ removeRepeat (xs)

-- 3. 
class IntDistCalculable a where 
    integerDist :: a -> a -> Integer
    
instance IntDistCalculable Bool where
    integerDist True True   = 0
    integerDist True False  = 1
    integerDist False True  = 1
    integerDist False False = 0

instance IntDistCalculable Integer where
    integerDist a b         = toInteger (abs (a - b))

instance IntDistCalculable Double where
    integerDist a b         = round (abs (a - b))

instance IntDistCalculable (Maybe a) where
    integerDist Nothing _   = 0
    integerDist _ Nothing   = 0
    integerDist _ _         = 1