package itschool.xcalculator;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

import itschool.xcalculator.databinding.ItemHistoryBinding;
import itschool.xcalculator.dto.HistoryItem;

public class HistoryAdapter extends RecyclerView.Adapter<HistoryAdapter.ViewHolder> {

    private ArrayList<HistoryItem> data = new ArrayList<>();
    private Consumer<HistoryItem> clickListener;

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_history, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.bind(data.get(position), clickListener);
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public void setData(ArrayList<HistoryItem> data) {
        this.data = data;
        notifyDataSetChanged();
    }

    public void insertItem(HistoryItem item) {
        data.add(item);
        notifyItemInserted(data.size() - 1);
    }

    public void setClickListener(Consumer<HistoryItem> clickListener) {
        this.clickListener = clickListener;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        private final TextDecorator decorator;
        private final ItemHistoryBinding binding;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            binding = ItemHistoryBinding.bind(itemView);
            decorator = new TextDecorator(itemView.getContext());
        }

        public void bind(@NonNull HistoryItem item, Consumer<HistoryItem> clickListener) {
            binding.getRoot().setOnClickListener((v) -> {
                if (clickListener != null) {
                    clickListener.accept(item);
                }
            });
            binding.expression.setText(decorator.decorate(item.getExpression()));
            binding.answer.setText(decorator.decorate(String.format("= %s", item.getAnswer())));
        }
    }
}
