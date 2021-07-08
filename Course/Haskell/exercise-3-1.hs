-- 7.
data PhoneType = WorkLandline | PrivateMobile | WorkMobile | Other deriving (Show, Eq, Read)

type CountryCode = Integer
type PhoneNo = Integer

data Phone = Phone {
    phoneType :: PhoneType, 
    countryCode :: CountryCode,
    phoneNo :: PhoneNo
} deriving (Show, Eq, Read)

makePhone :: PhoneType -> CountryCode  -> PhoneNo -> Phone
makePhone phoneType countryCode phoneNo
    | countryCode < 0   = error "Negative country code"
    | phoneNo < 0       = error "Negative phone number"
    | otherwise         = Phone phoneType countryCode phoneNo
