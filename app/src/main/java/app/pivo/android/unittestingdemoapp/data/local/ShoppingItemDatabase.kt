package app.pivo.android.unittestingdemoapp.data.local

/**
 * Created by murodjon on 2020/11/22
 */
import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    entities = [ShoppingItem::class],
    version = 1
)
abstract class ShoppingItemDatabase : RoomDatabase() {

    abstract fun shoppingDao(): ShoppingDao
}