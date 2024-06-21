package com.weathernexus.client.model

class Constants {
    interface URL_CONSTANTS{
        companion object{
            const val API_URL = "https://api.openweathermap.org/"
            const val IMG_URL = "https://openweathermap.org/img/wn//"
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

    interface ID_CITY_CONSTANTS{
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

    interface NAME_CITY_CONSTANTS{
        companion object{
            const val LAKE_ZURICH = "Lake Zurich"
            const val UPPER_HUNT = "Upper Hunt"
            const val DAVOS = "Davos"
            const val ALASKA = "Alaska"
            const val SAHARA_VILLAGE = "Sahara Village"
            const val SANDY_HILLS = "Sandy Hills"
            const val BELGRADE = "Belgrade"
            const val CALIFORNIA = "California"
        }
    }
}