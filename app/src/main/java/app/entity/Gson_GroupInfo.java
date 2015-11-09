package app.entity;

/**
 * Created by tlt on 2015/11/8.
 */
public class Gson_GroupInfo  {

      private String groupName;
      private String groupStyle;
      private String groupExpert;
      private String groupDescription;
      private String phones;

    public Gson_GroupInfo(String groupName, String groupStyle, String groupExpert, String groupDescription, String phones) {
        this.groupName = groupName;
        this.groupStyle = groupStyle;
        this.groupExpert = groupExpert;
        this.groupDescription = groupDescription;
        this.phones = phones;
    }

    @Override
    public String toString() {
        return "Gson_GroupInfo{" +
                "groupName='" + groupName + '\'' +
                ", groupStyle='" + groupStyle + '\'' +
                ", groupExpert='" + groupExpert + '\'' +
                ", groupDescription='" + groupDescription + '\'' +
                ", phones='" + phones + '\'' +
                '}';
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public String getGroupStyle() {
        return groupStyle;
    }

    public void setGroupStyle(String groupStyle) {
        this.groupStyle = groupStyle;
    }

    public String getGroupExpert() {
        return groupExpert;
    }

    public void setGroupExpert(String groupExpert) {
        this.groupExpert = groupExpert;
    }

    public String getGroupDescription() {
        return groupDescription;
    }

    public void setGroupDescription(String groupDescription) {
        this.groupDescription = groupDescription;
    }

    public String getPhones() {
        return phones;
    }

    public void setPhones(String phones) {
        this.phones = phones;
    }
}
