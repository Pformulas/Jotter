####author:macky
#NoteBookController

##根据笔记本获取他的笔记
- /show_notes_of_notebook.do	url
- (Integer page, String notebookId)	参数列表
- 页码，笔记本id   参数 
-  SUCCESS(0, "执行成功"),

##获取这个用户的所有笔记本
- /show_notebook_of_userId.do
- (Integer page, String userId)
- 页码，用户id
- SUCCESS(0, "执行成功"),

##根据笔记id查找一个笔记
- /show_note.do
- (String noteId)
- 笔记id
- SUCCESS(0, "执行成功"),

##根据笔记本id查找一个笔记本
- /show_notebook.do
- (String notebookId)
- 笔记本id
- - SUCCESS(0, "执行成功"),

##更新笔记本
- /update_notebook.do
- (NoteBook noteBook)
- userId notebookId notebookname
- SUCCESS(0, "执行成功"),

##更新笔记
- /update_note.do
- (Note note)
- userId notebookid noteid notetitle notedetail
- SUCCESS(0, "执行成功"),

##将一个笔记从一个笔记本移动到另外一个笔记本
- /move_note_to.do
- (Note note)
- userId,notebookId,noteId
- SUCCESS(0, "执行成功"),

##删除一个笔记
- /delete_note.do
- (Note note)
- userId  noteId
- SUCCESS(0, "执行成功"),

##删除笔记本，并且删除笔记本下的所有笔记
- /delete_notebook.do
- (NoteBook noteBook)
- userId notebookId
- SUCCESS(0, "执行成功"),

#File
##多个文件批量下载
- /multipleDownload.do
- (String[] fileNames, User user)
- fileNames  其实是file
- user   需要userId 和 username
- 下载的时候不需要提示

##删除文件
- /deleteFile.do
- String[] urls, User user)
- 一个url数组
- user需要username, userId
- FILE_DELETE_SUCCES(-7, "文件删除成功")

##根据文件的类型来列表
- /listFilesByType.do
- (User user, String type)
- user的userId  
- type是字符串，表示类别
- SUCCESS(0, "执行成功"),

