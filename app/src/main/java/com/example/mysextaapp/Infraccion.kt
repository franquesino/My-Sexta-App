package com.example.mysextaapp

import io.realm.kotlin.types.RealmObject
import io.realm.kotlin.types.annotations.PrimaryKey
import io.realm.kotlin.types.annotations.Index
import org.mongodb.kbson.ObjectId

class Infraccion : RealmObject {
    @PrimaryKey
    var id: ObjectId = ObjectId()

    var rutInspector: String = ""
    var nombreLocal: String = ""
    var direccion: String = ""
    var infraccion: String = ""
}
