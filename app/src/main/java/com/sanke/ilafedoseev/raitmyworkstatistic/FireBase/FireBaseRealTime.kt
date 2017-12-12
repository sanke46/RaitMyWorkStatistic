package com.sanke.ilafedoseev.raitmyworkstatistic.FireBase

import android.widget.TextView
import com.google.firebase.database.*

class FireBaseRealTime(val nameLocation: String ) {
    lateinit var listofStarsCounter : ArrayList<Int>
    lateinit var listOfLikesInTab : ArrayList<Int>

    var mDataBase : DatabaseReference = FirebaseDatabase.getInstance().getReference()
    var mDeviceRef = mDataBase.child("devices")
    var mTabloRef = mDataBase.child("tablo")
    var mDeviceLikesRef = mDataBase.child(nameLocation).child("devices").child("sam").child("likes")
    var mTabloLikesRef = mDataBase.child(nameLocation).child("tablo").child("tabloOne").child("likes")


    fun allTabLikesAndDislike(tabLikeNumber: TextView, tabDislikeNumber: TextView) {
        mTabloLikesRef.addValueEventListener(object : ValueEventListener {
            override fun onCancelled(p0: DatabaseError?) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

            override fun onDataChange(dataSnapshot : DataSnapshot?) {
                listOfLikesInTab = ArrayList<Int>(listOf(0,0))
                dataSnapshot?.children?.forEach { child : DataSnapshot ->
                    val objetTabMap : MutableMap<String, Any>
                    objetTabMap = child.value as MutableMap<String, Any>

                    for (entry in objetTabMap) {
                        if(entry.key.equals("value")) {
                            when(entry.value.toString()) {
                                "true" -> listOfLikesInTab.set(0, listOfLikesInTab.get(0) + 1)
                                "false" -> listOfLikesInTab.set(1, listOfLikesInTab.get(1) + 1)
                            }
                        }
                    }

                    tabLikeNumber.text = listOfLikesInTab.get(0).toString()
                    tabDislikeNumber.text = listOfLikesInTab.get(1).toString()

                }
            }

        })
    }

    fun allAverageLike(text: TextView, star1: TextView, star2: TextView, star3: TextView, star4: TextView, star5: TextView, allReview : TextView) {
        var count : Int
        var sumAllvalue : Int

        mDeviceLikesRef.addValueEventListener(object : ValueEventListener{
            override fun onCancelled(p0: DatabaseError?) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

            override fun onDataChange(dataSnapshot: DataSnapshot?) {
                listofStarsCounter = ArrayList<Int>(listOf(0,0,0,0,0))
                count = 0
                sumAllvalue = 0

                dataSnapshot?.children?.forEach { child : DataSnapshot ->

                    val objectMap : MutableMap<String, Any>
                    objectMap = child.value as MutableMap<String, Any>


                    for(entary in objectMap) {
                        if(entary.key.equals("value")) {
                            when(entary.value.toString()){
                                "1" -> listofStarsCounter.set(0, listofStarsCounter.get(0) + 1)
                                "2" -> listofStarsCounter.set(1, listofStarsCounter.get(1) + 1)
                                "3" -> listofStarsCounter.set(2, listofStarsCounter.get(2) + 1)
                                "4" -> listofStarsCounter.set(3, listofStarsCounter.get(3) + 1)
                                "5" -> listofStarsCounter.set(4, listofStarsCounter.get(4) + 1)
                            }
                            count++
                            sumAllvalue += entary.value.toString().toInt()
                        }
                    }
                }
                try {
                    text.text = "%.1f".format(sumAllvalue.toDouble() / count.toDouble())
                    allReview.text = "За все время : " + count.toString()
                    star1.text = "%.1f".format((100.0/count.toDouble()) * listofStarsCounter.get(0).toDouble()) + "%"
                    star2.text = "%.1f".format((100.0 / count.toDouble()) * listofStarsCounter.get(1).toDouble()) + "%"
                    star3.text = "%.1f".format((100.0 / count.toDouble()) * listofStarsCounter.get(2).toDouble()) + "%"
                    star4.text = "%.1f".format((100.0 / count.toDouble()) * listofStarsCounter.get(3).toDouble()) + "%"
                    star5.text = "%.1f".format((100.0 / count.toDouble()) * listofStarsCounter.get(4).toDouble()) + "%"
                } catch (e : ArithmeticException) {

                }


            }
        })
    }
}
