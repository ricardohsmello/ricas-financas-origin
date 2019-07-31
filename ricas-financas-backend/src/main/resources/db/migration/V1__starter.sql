CREATE SEQUENCE category_id_seq;
CREATE SEQUENCE finance_id_seq;
CREATE SEQUENCE user_id_seq;
CREATE SEQUENCE role_id_seq;

CREATE TABLE "categorys"
(
    "idcategory" INTEGER DEFAULT NEXTVAL('category_id_seq') NOT NULL,
    "type" numeric(1) NOT NULL,
    "name" text NOT NULL,
    CONSTRAINT pk_category PRIMARY KEY ("idcategory")
);
    
    
CREATE TABLE "finances" (
	"idfinance" INTEGER DEFAULT NEXTVAL('finance_id_seq'),
	"value" float, 
	"description" text,
	"idcategory" INTEGER NOT NULL,
    FOREIGN KEY ("idcategory") REFERENCES categorys ("idcategory"));
    
CREATE TABLE "users" (
  "iduser" INTEGER DEFAULT NEXTVAL('user_id_seq') NOT NULL,
  "name" text,
  "username" varchar UNIQUE NOT NULL,
  "password" varchar NOT NULL,
  "enabled" boolean NOT NULL,
  CONSTRAINT pk_user PRIMARY KEY ("iduser"));
   
  
  CREATE TABLE "role_users" (
  "idrole" int PRIMARY KEY,
  "iduser" int,
  "role" varchar NOT NULL);
  
  ALTER TABLE role_users ADD FOREIGN KEY ("iduser") REFERENCES "users" ("iduser");
  
);
