package com.example.repositary_search.db

import androidx.room.TypeConverter
import com.example.repositary_search.Retrofit.models.License
import com.example.repositary_search.Retrofit.models.Owner

class Converters {

    @TypeConverter
    fun fromLicense(license: License): String{
        return license.name
    }
    @TypeConverter
    fun toLicense(name: String): License{
        return License("", "", name, "", "", "")
    }

    @TypeConverter
    fun fromOwner(owner: Owner): String{
        return owner.login
    }
    @TypeConverter
    fun toOwner(login: String): Owner{
        return Owner(login, login, login, login, login, login, login, 0, login, login, login, login, login, false, login, login, login, login)
//        return Owner("","","","","","","",0,login,"","","","",false,"","","","")
    }

}