package UploadDownloadController.Helper;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * The tree node of the directory tree. Each node implement one folder, the root is the users root file which is automatically
 * named the user id.Each node stores the files info in that folder.
 */
public class TreeNode {
    private String folderName;
    private String folderID;
    //Key of the Hashmap should be the fileID
    private HashMap<String,File> files;
    private TreeNode parent;
    private ArrayList<TreeNode> children;

    /**
     * default constructor of the class
     */
    public TreeNode() {
        folderName = "";
        files = new HashMap<String, File>();
        parent = null;
        children = new ArrayList<TreeNode>();
        folderID="";
    }

    /**
     * constructor with params
     * @param parent new parent
     * @param children new children
     * @param folderName new folder name
     * @param files new files
     * @param folderID new folder id
     */
    public TreeNode(TreeNode parent, ArrayList children, String folderName, HashMap<String,File> files,String folderID){
        this.folderName=folderName;
        this.parent=parent;
        this.children=children;
        this.files = files;
        this.folderID=folderID;
    }

    /**
     * add a new file into files
     * @param fileId new file id
     * @param file new file node
     */
    public void addFile(String fileId,File file){
        files.put(fileId, file);
    }

    /**
     * add files to the tree node
     * @param files new files
     */
    public  void addFiles(HashMap<String,File> files){
        for(String file:files.keySet()){
            this.files.put(file, files.get(file));
        }
    }

    /**
     * child adder
     * @param child new child
     */
    public void addChild(TreeNode child){
            this.children.add(child);
    }

    /**
     * parent setter
     * @param parent new parent
     */
    public void setParent(TreeNode parent) {
        this.parent = parent;
    }

    /**
     * children setter
     * @param children new children
     */
    public void setChildren(ArrayList<TreeNode> children) {
        this.children = children;
    }

    /**
     * parent getter
     * @return parent
     */
    public TreeNode getParent() {
        return parent;
    }

    /**
     * children getter
     * @return children
     */
    public ArrayList<TreeNode> getChildren() {
        return children;
    }

    /**
     * folder name setter
     * @param folderName new folder name
     */
    public void setFolderName(String folderName) {
        this.folderName = folderName;
    }

    /**
     * files setter
     * @param files new files
     */
    public void setFiles(HashMap<String, File> files) {
        this.files = files;
    }

    /**
     * files getter
     * @return files
     */
    public HashMap<String,File> getFiles() {
        return files;
    }

    /**
     * folder name getter
     * @return folder name
     */
    public String getFolderName() {
        return folderName;
    }

    /**
     * folder id getter
     * @return folder id
     */
    public String getFolderID() {
        return folderID;
    }

    /**
     * filder id setter
     * @param folderID new folder id
     */
    public void setFolderID(String folderID) {
        System.out.println("In treeNode:" +folderID);
        this.folderID = folderID;
    }
}
