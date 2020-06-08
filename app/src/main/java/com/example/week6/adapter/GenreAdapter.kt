import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.recyclerview.widget.RecyclerView
import com.example.week6.R
import com.example.week6.models.GenreId


class GenreAdapter(private val genres: List<GenreId>, val context: Context) :
    RecyclerView.Adapter<GenreAdapter.GenreViewHolder>() {
    inner class GenreViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var genre: Button = itemView.findViewById(R.id.genre)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GenreViewHolder {
        return GenreViewHolder(
            LayoutInflater.from(context).inflate(R.layout.genre_item, null, false)
        )
    }

    override fun getItemCount(): Int {
        return genres.size
    }

    override fun onBindViewHolder(holder: GenreViewHolder, position: Int) {
        holder.genre.text = genres[position].name
    }
}
