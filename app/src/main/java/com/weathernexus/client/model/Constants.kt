package com.weathernexus.client.model

class Constants {
    interface URL_CONSTANTS{
        companion object{
            const val API_URL1 = "http://api.openweathermap.org/"
            const val API_URL2 = "https://openweathermap.org/"
        }
    }

    interface KEY{
        companion object{
            const val API_KEY = "1b7eeecd2ff64dc83e8dcf1f4cb2102b"
        }
    }

    interface FORMATTING{
        companion object{
            const val IMAGE_FORMAT = "@2x.png"
        }
    }

    interface CATEGORY_CONSTANTS{
        companion object{

            const val CLEAR = "CLEAR"
            const val CLOUDS = "CLOUDS"
            const val RAIN = "RAIN"
            const val SNOW = "SNOW"

            enum class WEATHER{
                Clear, Clouds, Rain, Snow
            }
        }
    }

    interface CITY_CONSTANTS{
        companion object{
            const val LAKE_ZURICH = 4899170
            const val UPPER_HUNT = 6244895
            const val DAVOS = 2661039
            const val ALASKA = 5879092
            const val SAHARA_VILLAGE = 5780908
            const val SANDY_HILLS = 5781070
            const val BELGRADE = 792680
            const val CALIFORNIA = 4350049
        }
    }
}