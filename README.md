# Springboot-mongodb-angularCRUD
hello dear,
This is a springboot project using the mongodb as database  and angular for the front-end side.Once you download the files you need to install 
some packages for the angular  from cmd : cd desktop/angular...end , after that use this command npm install and then ng serve -o .
Now the angular server is runing , you might be change the database name using in mongodb , in order to do that, go to   application properties :
spring.data.mongodb.uri=mongodb://localhost:27017/employeesdb change employeesdb , then use these commands : use dbname; and now the mongodb server is running , and after 
running the tomcat server all you need now is to run the springboot project.
Note : if you wanna check the database after changes use this command db.Employee.find();

