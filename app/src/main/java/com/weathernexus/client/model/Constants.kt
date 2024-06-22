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

            const val CLEAR = "Clear"
            const val CLOUDS = "Clouds"
            const val RAIN = "Rain"
            const val SNOW = "Snow"

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

    interface NAME_ID_CITY_CONSTANTS{
        companion object{
            val mapIdNameCity: Map<String, Int> = mapOf(
                NAME_CITY_CONSTANTS.LAKE_ZURICH to ID_CITY_CONSTANTS.LAKE_ZURICH,
                NAME_CITY_CONSTANTS.UPPER_HUNT to ID_CITY_CONSTANTS.UPPER_HUNT,
                NAME_CITY_CONSTANTS.DAVOS to ID_CITY_CONSTANTS.DAVOS,
                NAME_CITY_CONSTANTS.ALASKA to ID_CITY_CONSTANTS.ALASKA,
                NAME_CITY_CONSTANTS.SAHARA_VILLAGE to ID_CITY_CONSTANTS.SAHARA_VILLAGE,
                NAME_CITY_CONSTANTS.SANDY_HILLS to ID_CITY_CONSTANTS.SANDY_HILLS,
                NAME_CITY_CONSTANTS.BELGRADE to ID_CITY_CONSTANTS.BELGRADE,
                NAME_CITY_CONSTANTS.CALIFORNIA to ID_CITY_CONSTANTS.CALIFORNIA
            )

            val mapNameIdCity: Map<Int, String> = mapOf(
                ID_CITY_CONSTANTS.LAKE_ZURICH to NAME_CITY_CONSTANTS.LAKE_ZURICH,
                ID_CITY_CONSTANTS.UPPER_HUNT to NAME_CITY_CONSTANTS.UPPER_HUNT,
                ID_CITY_CONSTANTS.DAVOS to NAME_CITY_CONSTANTS.DAVOS,
                ID_CITY_CONSTANTS.ALASKA to NAME_CITY_CONSTANTS.ALASKA,
                ID_CITY_CONSTANTS.SAHARA_VILLAGE to NAME_CITY_CONSTANTS.SAHARA_VILLAGE,
                ID_CITY_CONSTANTS.SANDY_HILLS to NAME_CITY_CONSTANTS.SANDY_HILLS,
                ID_CITY_CONSTANTS.BELGRADE to NAME_CITY_CONSTANTS.BELGRADE,
                ID_CITY_CONSTANTS.CALIFORNIA to NAME_CITY_CONSTANTS.CALIFORNIA,
            )
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