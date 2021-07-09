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
    let countryCode
    | head sCountryCode == '+'          = read 
    in Phone WorkLandline 1 1

getPhoneType :: String -> PhoneType
getPhoneType sPhoneType
    | sPhoneType == "WorkLandline"      = WorkLandline
    | sPhoneType == "PrivateMobile"     = PrivateMobile
    | sPhoneType == "WorkMobile"        = WorkMobile
    | sPhoneType == "Other"             = Other
    | sPhoneType == ""                  = error "Missing phone type"
    | otherwise                         = error "Incorrect phone type"

isNum :: Char -> Bool
isNum x = x `elem` ['0'...'9']

isAllNum :: [Char] -> Bool
isAllNum x = all isNum x

getCountryCode :: String -> CountryCode
getCountryCode ""                   = error "Empty country code"
getCountryCode sCountryCode
    | head sCountryCode == '+'      = getCountryCode (tail sCountryCode)
    | isAllNum sCountryCode         = read sCountryCode :: Integer
    | otherwise                     = error "Incorrect country code"