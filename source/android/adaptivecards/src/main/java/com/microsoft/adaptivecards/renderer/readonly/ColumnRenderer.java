package com.microsoft.adaptivecards.renderer.readonly;

import android.content.Context;
import android.support.v4.app.FragmentManager;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.microsoft.adaptivecards.objectmodel.ContainerStyle;
import com.microsoft.adaptivecards.renderer.action.ActionElementRenderer;
import com.microsoft.adaptivecards.renderer.actionhandler.ICardActionHandler;
import com.microsoft.adaptivecards.renderer.inputhandler.IInputHandler;
import com.microsoft.adaptivecards.objectmodel.BaseCardElement;
import com.microsoft.adaptivecards.objectmodel.BaseCardElementVector;
import com.microsoft.adaptivecards.objectmodel.HostConfig;
import com.microsoft.adaptivecards.objectmodel.Column;
import com.microsoft.adaptivecards.renderer.BaseCardElementRenderer;
import com.microsoft.adaptivecards.renderer.registration.CardRendererRegistration;

import java.util.Vector;
import java.util.Locale;

public class ColumnRenderer extends BaseCardElementRenderer
{
    private ColumnRenderer()
    {
    }

    public static ColumnRenderer getInstance()
    {
        if (s_instance == null)
        {
            s_instance = new ColumnRenderer();
        }

        return s_instance;
    }

    @Override
    public View render(
            Context context,
            FragmentManager fragmentManager,
            ViewGroup viewGroup,
            BaseCardElement baseCardElement,
            Vector<IInputHandler> inputActionHandlerList,
            ICardActionHandler cardActionHandler,
            HostConfig hostConfig,
            ContainerStyle containerStyle)
    {
        Column column;
        if (baseCardElement instanceof Column)
        {
            column = (Column) baseCardElement;
        }
        else if ((column = Column.dynamic_cast(baseCardElement)) == null)
        {
            throw new InternalError("Unable to convert BaseCardElement to FactSet object model.");
        }

        LinearLayout.LayoutParams layoutParams;
        BaseCardElementVector baseCardElementVector = column.GetItems();
        setSpacingAndSeparator(context, viewGroup, column.GetSpacing(), column.GetSeparator(), hostConfig, false);

        View returnedView = CardRendererRegistration.getInstance().render(context, fragmentManager, null, column, baseCardElementVector, inputActionHandlerList, cardActionHandler, hostConfig, containerStyle);
        String columnSize = column.GetWidth().toLowerCase(Locale.getDefault());

        if (TextUtils.isEmpty(columnSize) || columnSize.equals(g_columnSizeAuto))
        {
            layoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.MATCH_PARENT);
            returnedView.setLayoutParams(layoutParams);
        }
        else if (columnSize.equals(g_columnSizeStretch))
        {
            layoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);
            layoutParams.weight = 1;
            returnedView.setLayoutParams(layoutParams);
        }
        else
        {
            try
            {
                float columnWeight = Float.parseFloat(columnSize);
                layoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
                layoutParams.width = 0;
                layoutParams.weight = columnWeight;
                returnedView.setLayoutParams(layoutParams);
            }
            catch (NumberFormatException numFormatExcep)
            {
                throw new IllegalArgumentException("Column Width (" + column.GetWidth() + ") is not a valid weight ('auto', 'stretch', <integer>).");
            }
        }

        if (column.GetSelectAction() != null)
        {
            returnedView.setClickable(true);
            returnedView.setOnClickListener(new ActionElementRenderer.ButtonOnClickListener(column.GetSelectAction(),inputActionHandlerList, cardActionHandler));
        }

        viewGroup.addView(returnedView);
        return returnedView;
    }

    private static ColumnRenderer s_instance = null;
    private final String g_columnSizeAuto = "auto";
    private final String g_columnSizeStretch = "stretch";
}
