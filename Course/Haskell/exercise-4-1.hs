-- 9.
-- Use the types from Task 8.
data PhoneType = WorkLandline | PrivateMobile | WorkMobile | Other deriving (Eq, Ord, Show, Read)
data CountryCode = CountryCode Integer deriving (Eq, Ord)
data PhoneNo = PhoneNo Integer deriving (Eq, Ord)

instance Show CountryCode where
    show (CountryCode cc)
        | cc < 0        = error "Negative country code"
        | otherwise     = "+" ++ show cc

instance Show PhoneNo where
    show (PhoneNo no) 
        | no < 0        = error "Negative phone number"
        | otherwise     = show no

instance Num CountryCode where
    fromInteger cc = CountryCode cc
    CountryCode (a) + CountryCode (b)   = CountryCode (a + b)
    CountryCode (a) - CountryCode (b)   = CountryCode (a - b)
    CountryCode (a) * CountryCode (b)   = CountryCode (a * b)

instance Num PhoneNo where
    fromInteger no = PhoneNo no
    PhoneNo (a) + PhoneNo (b)   = PhoneNo (a + b)
    PhoneNo (a) - PhoneNo (b)   = PhoneNo (a + b)
    PhoneNo (a) * PhoneNo (b)   = PhoneNo (a * b)

data Phone = Phone {
    phoneType :: PhoneType, 
    countryCode :: CountryCode,
    phoneNo :: PhoneNo
} deriving (Eq, Ord)

instance Show Phone where
    show (Phone phoneType countryCode phoneNo)    =
        show countryCode ++ " " ++ show phoneNo ++ " (" ++ show phoneType ++ ")"

-- Implement a readPhone function (String -> String -> String -> Phone)
readPhone :: String -> String -> String -> Phone
readPhone sPhoneType sCountryCode sPhoneNo =
    let phoneType = getPhoneType sPhoneType
        countryCode = checkCountryCode (getCountryCode sCountryCode)
        phoneNo = getPhoneNo sPhoneNo
    in Phone phoneType countryCode phoneNo

getPhoneType :: String -> PhoneType
getPhoneType sPhoneType
    | sPhoneType == "WorkLandline"      = WorkLandline
    | sPhoneType == "PrivateMobile"     = PrivateMobile
    | sPhoneType == "WorkMobile"        = WorkMobile
    | sPhoneType == "Other"             = Other
    | sPhoneType == ""                  = error "Missing phone type"
    | otherwise                         = error "Incorrect phone type"

isNum :: Char -> Bool
isNum x = x `elem` ['0', '1', '2', '3', '4', '5', '6', '7', '8', '9']

isAllNum :: [Char] -> Bool
isAllNum x = all isNum x

getCountryCode :: String -> Integer
getCountryCode ""                   = error "Empty country code"
getCountryCode sCountryCode
    | head sCountryCode == '+'      = getCountryCode (tail sCountryCode)
    | head sCountryCode == '-'      = if isAllNum (tail sCountryCode) 
                                        then error "Negative country code" 
                                        else error "Incorrect country code"
    | isAllNum sCountryCode         = read sCountryCode :: Integer
    | otherwise                     = error "Incorrect country code"

checkCountryCode :: Integer -> CountryCode
checkCountryCode cc
    | (toInteger cc) `elem` [1, 2, 86]        = CountryCode cc
    | otherwise                               = error "Unknown country code"

getPhoneNo :: String -> PhoneNo
getPhoneNo ""                       = error "Empty phone number"
getPhoneNo no
    | head no == '-'                = error "Negative phone number"
    | isAllNum no                   = PhoneNo (read no :: Integer)
    | otherwise                     = error "Incorrect phone number"