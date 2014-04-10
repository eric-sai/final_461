package UploadDownloadController.Helper;


/**
 * This class is used to print the directory tree of files which under download files folder.
 */
public class FilePrinter {
    private TreeNode root;
    private String jspTreePrinter,userId,userName;
    private double totalSize;
    private String projectPath;

    /**
     * This consturtor take a tree node, userid and username to print a file tree with specific user
     * @param treeNode   the tree node
     * @param userId     the user id
     * @param userName   the use name as the root name
     */
    public FilePrinter(TreeNode treeNode,String userId,String userName) {
        root=treeNode;
        jspTreePrinter="<ul id=\"LinkedList1\" class=\"LinkedList span8\">";
        this.userId=userId;
        this.userName=userName;
        totalSize=0;
        projectPath=System.getProperty("user.dir");
    }

    /**
     * this method is used to build html tree
     */
    public void jspTreeBuilder() {

        jspTreePrinter+="<li>"+userName+"<td align=right >" +
                "<A HREF='addFolder.jsp?userId="+userId+"&folderPath=/"+userId+"' style='float:right;'>[Add Folder]</A>" +
                "<A HREF='fileupload.jsp?userId="+userId+"&filePath=/"+userId+"' style='float:right;'>[Upload File]</A>" +
                "<A HREF='deleteFile.jsp?isFolder=1&fid=-1&folderID=-1&userId="+userId+"' style='float:right;'>[Delete Folder]</A>" +
                "<A HREF='shareFile.jsp?isFolder=1&fid=-1&folderID=-1&userId="+userId+"' style='float:right;'>[Share/Unshare Folder]</A></td>"+"<ul>";
        for(String fileid:root.getFiles().keySet()){
            totalSize+=Integer.valueOf(root.getFiles().get(fileid).getSize())/1024;
            String color="black";
            if(root.getFiles().get(fileid).isShared())
                 color="red";
            jspTreePrinter+="<li>"+"<tr><td align=left><span style=\"color: "+color+"\">"+root.getFiles().get(fileid).getFileName()+"&nbsp;&nbsp;"+Integer.valueOf(root.getFiles().get(fileid).getSize())/1024+"&nbsp;kb </span></td><td align=right >" +
                   "<A style='float:right;' HREF='downloadFiles/"+root.getFiles().get(fileid).getFilePath()+"' style='float:right;'>[Download File]</A>" +
                    "<A HREF='deleteFile.jsp?isFolder=0&fid="+fileid+"&fids=-1&userId="+userId+"' style='float:right;'>[Delete File]</A>" +
                    "<A HREF='shareFile.jsp?isFolder=0&fid="+fileid+"&folderID=-1&userId="+userId+"' style='float:right;'>[Share/Unshare File]</A></td></tr>"+"</li>" ;
        }
        for(int i=0;i<root.getChildren().size();i++){
            recursivePrinter(root.getChildren().get(i));
        }
        jspTreePrinter+="</ul></li>";
        jspTreePrinter+="</ul>";
    }

    /**
     * this method is used to travel all node of tree
     * @param treeNode  the node of tree
     */
    private void recursivePrinter(TreeNode treeNode) {
        TreeNode filePathNavigator=treeNode;
        String filePath="";
        String folderPath;
        while(filePathNavigator.getParent()!=null){
            filePath="/"+filePathNavigator.getFolderName()+filePath;
            filePathNavigator=filePathNavigator.getParent();
        }
        filePath="/"+filePathNavigator.getFolderName()+filePath;
        folderPath=filePath;

        jspTreePrinter+="<li>"+treeNode.getFolderName()+"<td align=\"right\" width=\'500\'>" +
                "<A HREF='addFolder.jsp?userId="+userId+"&folderPath="+folderPath+"' style='float:right;'>[Add Folder]</A>" +
                "<A style='float:right;' HREF='fileupload.jsp?userId="+userId+"&filePath="+filePath+"' style='float:right;'>[Upload File]</A>" +
                "<A HREF='deleteFile.jsp?isFolder=1&folderID="+treeNode.getFolderID()+"&folderID="+treeNode.getFolderID()+"&userId="+userId+"' style='float:right;'>[Delete Folder]</A>" +
                "<A HREF='shareFile.jsp?isFolder=1&fid=-1&folderID="+treeNode.getFolderID()+"&userId="+userId+"' style='float:right;' onclick='return share(); '>[Share/Unshare Folder]</A></td>"+"<ul>";

        for(String fileid:treeNode.getFiles().keySet()){
            totalSize+=Integer.valueOf(treeNode.getFiles().get(fileid).getSize())/1024;
            String color="black";
            if(treeNode.getFiles().get(fileid).isShared())
                color="red";

            jspTreePrinter+="<li style=\"color: ="+color+"\">"+"<tr><td align=left width=\"500\"><span style=\"color: "+color+"\">"+treeNode.getFiles().get(fileid).getFileName()+"&nbsp;&nbsp;"+Integer.valueOf(treeNode.getFiles().get(fileid).getSize())/1024+"&nbsp;kb </span></td><td align=right width=\'500\'>" +
                    "<A style='float:right;' HREF='downloadFiles/"+treeNode.getFiles().get(fileid).getFilePath()+"' style='float:right;'>[Download File]</A>" +
                    "<A HREF='deleteFile.jsp?isFolder=0&fid="+fileid+"&folderID=-1&userId="+userId+"' style='float:right;'>[Delete]</A>" +
                    "<A HREF='shareFile.jsp?isFolder=0&fid="+fileid+"&folderID=-1&userId="+userId+"' style='float:right;'>[Share/Unshare File]</A></td></tr>"+"</li>";
        }
        for(int i=0;i<treeNode.getChildren().size();i++){
            recursivePrinter(treeNode.getChildren().get(i));
        }
        jspTreePrinter+="</ul></li>";
    }

    /**
     * return the result of html
     * @return the result of html
     */
    public String getJspTreePrinter(){
        return jspTreePrinter;
    }

    /**
     * this method is used to calculate the total disk use of a specific user
     * @return   the size in double
     */
    public double getUserTotalSizeUsed(){
        return totalSize;
    }
}
