package fastcampus.aop.part3.aop_part3_chapter02

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class QuotesPagerAdapter(
    private val quotes: List<Quote>
): RecyclerView.Adapter<QuotesPagerAdapter.QuotesViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): QuotesViewHolder =
        QuotesViewHolder(
            LayoutInflater
                .from(parent.context)
                .inflate(R.layout.item_quote, parent, false)
        )


    override fun onBindViewHolder(holder: QuotesViewHolder, position: Int) {
        holder.bind(quotes[position])
    }

    override fun getItemCount(): Int = quotes.size

    class QuotesViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {

        private val quoteTextView: TextView = itemView.findViewById(R.id.quoteTextView)
        private val nameTextView: TextView = itemView.findViewById(R.id.nameTextView)

        fun bind(quote: Quote) {
            quoteTextView.text = quote.quote
            nameTextView.text = quote.name
        }
    }
}