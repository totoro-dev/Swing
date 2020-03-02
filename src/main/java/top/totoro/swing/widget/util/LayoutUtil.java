package top.totoro.swing.widget.util;

import top.totoro.swing.widget.base.BaseLayout;
import top.totoro.swing.widget.bean.LayoutAttribute;
import top.totoro.swing.widget.exception.LayoutException;
import top.totoro.swing.widget.view.View;

import java.lang.reflect.InvocationTargetException;

public class LayoutUtil {
    private static final String LAYOUT_PACKAGE = "top.totoro.swing.widget.layout.";

    public static <T extends BaseLayout> T createLayout(View parent, String layoutName, LayoutAttribute attribute) {
        Class clazz = null;
        try {
            if (layoutName == null) {
                throw new LayoutException(attribute.getResName() + "文件中Layout节点解析错误。");
            } else if (layoutName.contains(".")) {
                clazz = Class.forName(layoutName);
            } else if (layoutName.endsWith("Layout")) {
                clazz = Class.forName(LAYOUT_PACKAGE + layoutName);
            } else {
                return null;
//                throw new LayoutException(attribute.getResName() + "文件中的 " + layoutName + " 不存在。");
            }
            T layout = (T) clazz.getConstructor(View.class).newInstance(parent);
            layout.setAttribute(attribute);
            return layout;
        } catch (LayoutException | ClassNotFoundException | InstantiationException | IllegalAccessException | NoSuchMethodException | InvocationTargetException e) {
            e.printStackTrace();
        }
        return null;
    }
}
