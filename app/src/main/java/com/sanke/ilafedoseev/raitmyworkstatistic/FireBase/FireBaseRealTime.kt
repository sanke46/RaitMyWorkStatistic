package com.sanke.ilafedoseev.raitmyworkstatistic.FireBase

import android.widget.TextView
import com.google.firebase.database.*

class FireBaseRealTime {

    var mDataBase : DatabaseReference = FirebaseDatabase.getInstance().getReference()
    var mDeviceRef = mDataBase.child("devices")
    var mTabloRef = mDataBase.child("tablo")
    var mDeviceLikesRef = mDataBase.child("service").child("devices").child("sam").child("likes")
    var mTabloLikesRef = mDataBase.child("tablo").child("sam").child("likes")

    fun allAverageLike(text : TextView) {
        var count : Int = 0
        var sumAllvalue : Int = 0

        mDeviceLikesRef.addValueEventListener(object : ValueEventListener{
            override fun onCancelled(p0: DatabaseError?) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

            override fun onDataChange(dataSnapshot: DataSnapshot?) {
                for (child : DataSnapshot in dataSnapshot?.children!!) {
                    var objectMap : Map<String, Any>
                    objectMap = child.value as Map<String, Any>;

                    for(entary in objectMap) {
                        if(entary.key.equals("value")) {
                            count++
                            sumAllvalue += entary.value.toString().toInt()
                        }
                    }
                }

                println(sumAllvalue) // 9
                println(count) // 3
                println("!!!!!!!!!!!!")

                text.text = "%.1f".format(sumAllvalue.toDouble()/count.toDouble())
            }
        })
    }
}
