-- 8.
data PhoneType = WorkLandline | PrivateMobile | WorkMobile | Other deriving (Eq, Ord, Show)
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