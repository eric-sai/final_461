
package historyMachineController;


import UploadDownloadController.Helper.File;
import UploadDownloadController.Helper.TreeNode;
import model.FileDirEntity;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * this is a file tree of all files in disk
*/
public class FileDirectionary {
    private FileDirEntity parent;
    private Integer fid;
    private String directoryTree;
   private List<FileDirEntity> children = new LinkedList<FileDirEntity>();
    private TreeNode root;
    private TreeNode tem2;
    private String dir;
    private String Folder="";

    /**
     * the root constructor
     */
     public FileDirectionary()
            {
                root=new TreeNode() ;
                 tem2=new TreeNode();

            }

    /**
     * this method is used to build the file tree
     * @param uid user id
     * @return the tree node
     */
  public TreeNode buildTree(String uid)
  {
      InsertTable s=new InsertTable() ;
      List<List> temp=s.directoryTreeselect(uid);

      for(List list:temp){
          String fd=list.toString();
          fd= fd.substring(1,fd.length()-1)  ;
       //          System.out.println(fd);
          String[] strs=fd.split("/");
        //  root.setFolderID(new FileInfoGet().getFilID(strs[0]) );
          Folder=strs[0];
          root.setFolderName(strs[0]);
       //   System.out.println("rt:"+root.getFolderName());
          tem2=root;
           dir=fd;
          //   int size2=strs.length;
          for(String st:strs)
          {

              if(!st.equals(tem2.getFolderName()))
              {
           //       System.out.println("directory :"+dir);
                if(st.contains("."))
                {
                    String fid=new FileInfoGet().getFilID(dir,uid) ;
                //    fid=fid.substring(1, fid.length() - 1);
                    String fN=new FileInfoGet().getFileName(fid,uid) ;
                    String ft=new FileInfoGet().getFileType(fid,uid) ;
                    String fs=new FileInfoGet().getFileSize(fid,uid);
                    String fc=new FileInfoGet().getFileCreateDate(fid,uid);
                    boolean fsh=new FileInfoGet().getFileSizeShared(fid,uid);
            //        fN=fN.substring(1,fN.length()-1)  ;
                    File file=new File(fN,fsh,ft,dir,fc,fs);
                    file.setFileName(st);
                    file.setFilePath(dir);
                        tem2.addFile(fid,file);
                //    System.out.println("s="+dir);
                }
             //     System.out.println("tem2"+tem2.getFolderName());
                 else if(!checkTree(tem2,st))
                  {
                   //   System.out.println("st:"+st);
                      Folder=Folder+"/"+st;

                      System.out.println("check&add2ndFolder:" +Folder);
                       System.out.println("check&add2ndFolder"+uid);
                      TreeNode tem1=new TreeNode();
                      tem1.setFolderID(new FileInfoGet().getFilID(Folder,uid) );
                      tem1.setFolderName(st);
                       tem1.setParent(tem2);
                      tem2.addChild(tem1);
                      tem2=tem1;
         //             dir=dir+"/"+st;
                 //     size++;
                  }


              //    tem1.setParent(tem);
               //   tem.addChild(tem1);
               //   tem=tem1;
              }
          }
      }
       return root;
  }

    /**
     * check the tree with specific node or dir
     * @param node       the node
     * @param dir                the dir
     * @return   true if it is found the node
     */
    public boolean checkTree(TreeNode node,String dir)
    {

        boolean flag=false;
        if(node.getChildren().size()!=0)
        {
       for(int i=0;i<node.getChildren().size();i++)
       {
           if(node.getChildren().get(i).getFolderName().equals(dir))
           {
               flag=true;
               tem2=node.getChildren().get(i);
               tem2.setFolderName(node.getChildren().get(i).getFolderName());
               Folder=Folder+"/"+node.getChildren().get(i).getFolderName();
             //   dir=dir+"/"+node.getChildren().get(i).getFolderName();

           }

       }
        }
            return flag;
    }

   public void printTree(TreeNode root1)
   {
       System.out.println(root1.getFolderName());
       if(!root1.getFiles().toString().isEmpty())
           System.out.println("File Information: "+root1.getFiles().toString());
       for(int i=0;i<root1.getChildren().size();i++){

           printTree(root1.getChildren().get(i));
       }
   }

    /**
     * get the array of all nodes
     * @param f a file array
     * @return all nodes in array
     */
    public  ArrayList<String> getArray(String[][] f) {
        String str ="";
        ArrayList<String> sarr=new ArrayList<String>();
        for (int i = 0; i < f.length; i++) {
            for (int j = 0; j < f[i].length; j++) {
                if(f[i][j]!=null)
                {
                    str=str+f[i][j] + " ";
                //    System.out.println();
              //      System.out.println(str);
                }
                }
            if(str!="")
            sarr.add(str);
            str ="";
          //  System.out.println();
        }
        return sarr;
    }
        public String[][] convert(String[][] b) {

            String [][] temp = new String[b[0].length][b.length];
            for (int i = 0; i < b.length; i++) {
                for (int j = 0; j < b[i].length; j++) {
                    if(b[b[i].length-j-1][i]!=null)
                    {
                    temp[i][j]=b[b[i].length-j-1][i];
                   // str=str+b[b[i].length-j-1][i];

                    }
                }
            }
            return temp;

    }

    /**
     * return the parent of file
     * @return  file dir of parent
     */
    public FileDirEntity getParent() {
        return parent;
    }
    /**
     * set the parent of file
     * @return  file dir of parent
     */
    public void setParent(FileDirEntity parent) {
        this.parent = parent;
    }
    /**
     * return file id
     * @return  fid
     */
    public Integer getFid() {
        return fid;
    }
    /**
     * set file id
     *
     */
    public void setFid(Integer fid) {
        this.fid = fid;
    }

    public String getDirectoryTree() {
        return directoryTree;
    }

    public void setDirectoryTree(String directoryTree) {
        this.directoryTree = directoryTree;
    }

    public List<FileDirEntity> getChildren() {
        return children;
    }

    public void setChildren(List<FileDirEntity> children) {
        this.children = children;
    }
}

