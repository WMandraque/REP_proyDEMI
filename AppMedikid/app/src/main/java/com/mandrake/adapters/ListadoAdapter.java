package com.mandrake.adapters;

import android.content.Context;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.mandrake.appmedikid.R;
import com.mandrake.model.AtencionModel;
import com.mandrake.utils.Constantes;
import com.mandrake.utils.HTMLConstantes;

import java.util.ArrayList;

/**
 * Created by EDSON on 12/07/2015.
 */
public class ListadoAdapter extends BaseAdapter {

    Context context;
    ArrayList<AtencionModel> lista;

    public ListadoAdapter(Context context, ArrayList<AtencionModel> lista) {
        this.context = context;
        this.lista = lista;
    }

    @Override
    public int getCount() {
        return lista.size();
    }

    @Override
    public Object getItem(int position) {
        return lista.get(position);
    }

    @Override
    public long getItemId(int position) {
        return lista.get(position).getId();
    }

    static class ViewHolder {
        TextView lblId, lblMedico, lblFechaAtencion,
                 lblDiagnostico, lblReceta;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(context)
                    .inflate(R.layout.item_helper, parent, false);
            ViewHolder viewHolder = new ViewHolder();

            viewHolder.lblId = (TextView)convertView.findViewById(R.id.lblId);
            viewHolder.lblMedico = (TextView)convertView.findViewById(R.id.lblMedico);
            viewHolder.lblFechaAtencion = (TextView)convertView.findViewById(R.id.lblFechaAtencion);
            viewHolder.lblDiagnostico = (TextView)convertView.findViewById(R.id.lblDiagnostico);
            viewHolder.lblReceta = (TextView)convertView.findViewById(R.id.lblReceta);

            convertView.setTag(viewHolder);
        }
        AtencionModel entidad = lista.get(position);

        ViewHolder holder = (ViewHolder) convertView.getTag();
        holder.lblId.setText(entidad.getId());
        holder.lblMedico.setText(Html.fromHtml(formatText(Constantes.DESC_MEDICO, entidad.getMedico())));
        holder.lblFechaAtencion.setText(Html.fromHtml(formatText(Constantes.DESC_FECHAATENCION, entidad.getFechaAtencion())));
        holder.lblReceta.setText(Html.fromHtml(formatText(Constantes.DESC_RECETA, entidad.getReceta())));
        holder.lblDiagnostico.setText(Html.fromHtml(formatText(Constantes.DESC_DIAGNOSTICO, entidad.getDiagnostico())));

        return convertView;
    }

    private String formatText(String desc, String value){
        return String.format(HTMLConstantes.CADENA_DESC_BOLD, desc, value);
    }

}
