INSERT INTO  persons (firstname, lastname,emailAddress,username,password, enabled) VALUES ('Curious','George','admin@admin.com','admin','$2a$10$S/wlXEo/APzf.Sn1cO2p4.V12EJmaw.uzrHelMvkpuahjmHWnSafe', TRUE);
INSERT INTO persons (firstname, lastname,emailAddress,username,password, enabled) VALUES ('Allen','Rench','guest@guest.com','guest','$2a$10$0.ESlGysrPaiW5HaapKwoehzWt5AibgbPPOvMhDv8D6H26QQ/CwhS', TRUE);
INSERT INTO persons (firstname, lastname,emailAddress,username,password, enabled) VALUES ('Dipesh','Rijal','counsel@counsel.com','counselor','$2a$10$S/wlXEo/APzf.Sn1cO2p4.V12EJmaw.uzrHelMvkpuahjmHWnSafe', TRUE);

INSERT INTO authorities (authority, username) VALUES ('ROLE_ADMIN', 1);
INSERT INTO authorities (authority, username) VALUES ('ROLE_USER', 2);
INSERT INTO authorities (authority, username) VALUES ('ROLE_COUNSELOR', 3);
 
					