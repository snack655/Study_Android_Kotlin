package kr.co.study.androidtob1nd

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class MovieAdapter: RecyclerView.Adapter<MovieAdapter.MovieViewHolder>() {

    val itemList = ArrayList<Movie>()

    class MovieViewHolder(private val view: View): RecyclerView.ViewHolder(view) {
        fun bind(movie: Movie) {
            view.findViewById<TextView>(R.id.tv_title).text = Movie.movieNm
            view.findViewById<TextView>(R.id.tv_genre).text = Movie.repGenreNm
            view.findViewById<TextView>(R.id.tv_open_data).text = Movie.openDt
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        return MovieViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_movie, parent, false))
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        holder.bind(itemList[position])
    }

    override fun getItemCount(): Int {
        return itemList.size
    }
}