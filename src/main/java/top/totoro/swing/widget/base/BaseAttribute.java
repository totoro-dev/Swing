package top.totoro.swing.widget.base;

import org.dom4j.Attribute;
import top.totoro.swing.widget.exception.AttributeException;
import top.totoro.swing.widget.util.AttributeDefaultValue;

import java.net.URL;

public class BaseAttribute {

    private String resName = "";
    private String nodeName = "";

    public static final int OPAQUE = 0; // 不透明
    public static final int NOT_OPAQUE = 1; // 透明

    // 组件自适应
    public static final int MATCH_PARENT = -2;
    public static final int WRAP_CONTENT = -1;
    // 组件是否可见
    public static final int GONE = 0;
    public static final int VISIBLE = 1;

    private String id = String.valueOf(System.currentTimeMillis());
    private int opaque = OPAQUE; // 背景是否透明，默认不透明
    private int startX = 0;
    private int startY = 0;
    private int width = 0;
    private int height = 0;
    private int visible = VISIBLE;

    private String background = "#ffffff";
    private String src = "";

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getOpaque() {
        return opaque;
    }

    public void setOpaque(int opaque) {
        this.opaque = opaque;
    }

    public int getStartX() {
        return startX;
    }

    public void setStartX(int startX) {
        this.startX = startX;
    }

    public int getStartY() {
        return startY;
    }

    public void setStartY(int startY) {
        this.startY = startY;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getVisible() {
        return visible;
    }

    public void setVisible(int visible) {
        this.visible = visible;
    }

    public String getBackground() {
        return background;
    }

    public void setBackground(String background) {
        this.background = background;
    }

    public String getSrc() {
        return src;
    }

    public void setSrc(String src) {
        this.src = src;
    }

    public boolean checkHeightValue(Attribute attr) {
        try {
            if (attr == null) {
                throw new AttributeException("组件" + nodeName + "必须指定height属性");
            }
            String value = attr.getValue();
            if (value == null) {
                throw new AttributeException("组件" + nodeName + "必须指定height属性");
            }
            if (value.equals(AttributeDefaultValue.MATCH_PARENT)) {
                setHeight(BaseAttribute.MATCH_PARENT);
            } else if (value.equals(AttributeDefaultValue.WRAP_CONTENT)) {
                setHeight(BaseAttribute.WRAP_CONTENT);
            } else if (isUnsignedInt(value)) {
                setHeight(Integer.parseInt(value));
            } else {
                throw new AttributeException("组件" + nodeName + "的height属性必须是 : 正整数、match_parent、wrap_content");
            }
        } catch (AttributeException e) {
            e.printStackTrace();
        }
        return true;
    }

    public boolean checkWidthValue(Attribute attr) {
        try {
            if (attr == null) {
                throw new AttributeException("组件" + nodeName + "必须指定width属性");
            }
            String value = attr.getValue();
            if (value == null) {
                throw new AttributeException("组件" + nodeName + "必须指定width属性");
            }
            if (value.equals(AttributeDefaultValue.MATCH_PARENT)) {
                setWidth(BaseAttribute.MATCH_PARENT);
            } else if (value.equals(AttributeDefaultValue.WRAP_CONTENT)) {
                setWidth(BaseAttribute.WRAP_CONTENT);
            } else if (isUnsignedInt(value)) {
                setWidth(Integer.parseInt(value));
            } else {
                throw new AttributeException("组件" + nodeName + "的width属性必须是 : 正整数、match_parent、wrap_content");
            }
        } catch (AttributeException e) {
            e.printStackTrace();
        }
        return true;
    }

    public boolean isUnsignedInt(String value) {
        if (value == null || value.length() == 0) return false;
        char[] cs = value.toCharArray();
        for (char c : cs) {
            int ascii = Integer.parseInt(Integer.toString(c));
            if (ascii >= 48 && ascii <= 57) {
                continue;
            }
            return false;
        }
        return true;
    }

    public boolean isColor(String value) {
        if (value == null || value.length() != 7 || !value.startsWith("#")) return false;
        char[] cs = value.substring(1).toCharArray();
        for (char c : cs) {
            int ascii = Integer.parseInt(Integer.toString(c));
            if ((ascii >= 48 && ascii <= 57) || (ascii >= 65 && ascii <= 70) || (ascii >= 97 && ascii <= 102)) {
                continue;
            }
            return false;
        }
        return true;
    }

    public boolean isSrcPath(String value) {
        if (value == null || value.length() == 0) return false;
        URL url = getClass().getClassLoader().getResource(value);
        if (url == null) {
            return false;
        }
        return true;
    }

    public String getResName() {
        return resName;
    }

    public void setResName(String resName) {
        this.resName = resName;
    }

    public String getNodeName() {
        return nodeName;
    }

    public void setNodeName(String nodeName) {
        this.nodeName = nodeName;
    }

    @Override
    public String toString() {
        return "BaseAttribute{" +
                "resName='" + resName + '\'' +
                ", nodeName='" + nodeName + '\'' +
                ", id='" + id + '\'' +
                ", opaque=" + opaque +
                ", startX=" + startX +
                ", startY=" + startY +
                ", width=" + width +
                ", height=" + height +
                ", visible=" + visible +
                ", background='" + background + '\'' +
                ", src='" + src + '\'' +
                '}';
    }
}
