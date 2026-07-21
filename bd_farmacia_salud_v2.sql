-- ============================================================
--  FARMACIA SALUDPLUS v2 — Script BD con tabla clientes
-- ============================================================
DROP DATABASE IF EXISTS bd_farmacia_salud;
CREATE DATABASE bd_farmacia_salud CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
USE bd_farmacia_salud;

CREATE TABLE categorias (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL UNIQUE,
    descripcion VARCHAR(300),
    created_at DATETIME DEFAULT CURRENT_TIMESTAMP,
    updated_at DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);

CREATE TABLE proveedores (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(150) NOT NULL,
    ruc VARCHAR(11) NOT NULL UNIQUE,
    direccion VARCHAR(200),
    telefono VARCHAR(20),
    email VARCHAR(120),
    activo TINYINT(1) NOT NULL DEFAULT 1,
    created_at DATETIME DEFAULT CURRENT_TIMESTAMP,
    updated_at DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);

CREATE TABLE clientes (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(150) NOT NULL,
    dni VARCHAR(8) NOT NULL UNIQUE,
    telefono VARCHAR(20),
    email VARCHAR(120),
    direccion VARCHAR(200),
    fecha_nacimiento DATE,
    activo TINYINT(1) NOT NULL DEFAULT 1,
    created_at DATETIME DEFAULT CURRENT_TIMESTAMP,
    updated_at DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);

CREATE TABLE usuarios (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(60) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL,
    nombre_completo VARCHAR(120) NOT NULL,
    rol ENUM('ADMIN','EMPLEADO') NOT NULL,
    activo TINYINT(1) NOT NULL DEFAULT 1
);

CREATE TABLE medicamentos (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(200) NOT NULL,
    principio_activo VARCHAR(150) NOT NULL,
    presentacion VARCHAR(100),
    concentracion VARCHAR(50),
    precio_venta DOUBLE NOT NULL,
    precio_compra DOUBLE,
    stock INT NOT NULL DEFAULT 0,
    stock_minimo INT NOT NULL DEFAULT 5,
    fecha_vencimiento DATE,
    requiere_receta TINYINT(1) NOT NULL DEFAULT 0,
    activo TINYINT(1) NOT NULL DEFAULT 1,
    categoria_id BIGINT NOT NULL,
    proveedor_id BIGINT NOT NULL,
    created_at DATETIME DEFAULT CURRENT_TIMESTAMP,
    updated_at DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    CONSTRAINT fk_med_cat  FOREIGN KEY (categoria_id) REFERENCES categorias(id),
    CONSTRAINT fk_med_prov FOREIGN KEY (proveedor_id) REFERENCES proveedores(id)
);

CREATE TABLE ventas (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    cliente_id BIGINT,          -- nullable: puede ser venta sin cliente registrado
    total_venta DOUBLE NOT NULL DEFAULT 0,
    estado ENUM('COMPLETADA','ANULADA') NOT NULL DEFAULT 'COMPLETADA',
    observacion VARCHAR(300),
    usuario_id BIGINT NOT NULL,
    fecha_venta DATETIME DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT fk_venta_cli  FOREIGN KEY (cliente_id)  REFERENCES clientes(id),
    CONSTRAINT fk_venta_user FOREIGN KEY (usuario_id)  REFERENCES usuarios(id)
);

CREATE TABLE detalle_ventas (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    venta_id BIGINT NOT NULL,
    medicamento_id BIGINT NOT NULL,
    cantidad INT NOT NULL,
    precio_unitario DOUBLE NOT NULL,
    subtotal DOUBLE NOT NULL,
    CONSTRAINT fk_det_venta FOREIGN KEY (venta_id)       REFERENCES ventas(id),
    CONSTRAINT fk_det_med   FOREIGN KEY (medicamento_id) REFERENCES medicamentos(id)
);

-- ============================================================
-- DATOS
-- ============================================================
INSERT INTO categorias (nombre, descripcion) VALUES
('Analgésicos',           'Medicamentos para el alivio del dolor'),
('Antibióticos',          'Medicamentos para combatir infecciones bacterianas'),
('Antiinflamatorios',     'Medicamentos para reducir inflamación'),
('Antihistamínicos',      'Medicamentos para alergias y reacciones alérgicas'),
('Vitaminas y Suplementos','Vitaminas, minerales y suplementos nutricionales'),
('Antidiabéticos',        'Medicamentos para control de glucosa en sangre'),
('Antihipertensivos',     'Medicamentos para control de presión arterial'),
('Gastrointestinales',    'Medicamentos para problemas digestivos y gástricos'),
('Dermatológicos',        'Medicamentos para uso tópico en piel'),
('Respiratorios',         'Medicamentos para sistema respiratorio y bronquios');

INSERT INTO proveedores (nombre, ruc, direccion, telefono, email) VALUES
('Laboratorios Bayer Peru S.A.',    '20100055948', 'Av. Paseo de la República 3220, Lima', '01-6128200', 'contacto@bayer.pe'),
('Farmaindustria S.A.C.',           '20378896161', 'Jr. Callao 385, Lima Centro',           '01-4272010', 'ventas@farmaindustria.com.pe'),
('Química Suiza S.A.',              '20100085227', 'Av. El Derby 250, Santiago de Surco',   '01-3198000', 'info@quimicasuiza.com'),
('Medifarma S.A.',                  '20100148954', 'Carretera Central Km. 10, Lima',        '01-3629777', 'ventas@medifarma.com.pe'),
('Distribuidora Alfa Medic S.A.C.', '20524131641', 'Jr. Arequipa 1240, Cercado de Lima',   '01-4215544', 'alfamedic@gmail.com');

INSERT INTO clientes (nombre, dni, telefono, email, direccion, fecha_nacimiento) VALUES
('Juan Pérez Torres',     '45678901', '987654321', 'juan.perez@gmail.com',    'Jr. Los Pinos 123, Surco',         '1985-03-15'),
('Ana Flores Quispe',     '32156789', '956123478', 'ana.flores@hotmail.com',  'Av. Arequipa 456, Miraflores',     '1990-07-22'),
('Luis Mamani Huanca',    '78234561', '945678123', NULL,                      'Calle Real 789, San Borja',        '1978-11-08'),
('Rosa Condori Puma',     '56123478', '934567891', 'rosa.condori@gmail.com',  'Jr. Lima 321, Ate',                '1995-01-30'),
('Pedro Sánchez Huanca',  '12345678', '912345678', 'pedro.s@outlook.com',     'Av. Universitaria 567, Los Olivos','1988-09-14'),
('Carmen Rios Valdivia',  '67890123', '998765432', 'carmen.rios@gmail.com',   'Jr. Bolognesi 234, Breña',         '1972-05-20'),
('Miguel Torres Ccallo',  '89012345', '976543210', NULL,                      'Av. Túpac Amaru 890, Comas',       '2000-12-01'),
('Lucía Vargas Mendoza',  '23456789', '965432109', 'lucia.v@gmail.com',       'Calle Colón 112, Magdalena',       '1993-08-17');

-- Usuarios (BCrypt de "Admin@2025" y "Empleado@2025")
INSERT INTO usuarios (username, password, nombre_completo, rol) VALUES
('admin',     '$2a$10$92IXUNpkjO0rOQ5byMi.Ye4oKoEa3Ro9llC/.og/at2uheWG/igi.', 'Administrador del Sistema', 'ADMIN'),
('empleado1', '$2a$10$92IXUNpkjO0rOQ5byMi.Ye4oKoEa3Ro9llC/.og/at2uheWG/igi.', 'María García López',        'EMPLEADO'),
('empleado2', '$2a$10$92IXUNpkjO0rOQ5byMi.Ye4oKoEa3Ro9llC/.og/at2uheWG/igi.', 'Carlos Quispe Mamani',      'EMPLEADO');

INSERT INTO medicamentos (nombre,principio_activo,presentacion,concentracion,precio_venta,precio_compra,stock,stock_minimo,fecha_vencimiento,requiere_receta,categoria_id,proveedor_id) VALUES
('Paracetamol 500mg',         'Paracetamol',         'Tabletas',    '500mg',      3.50,  1.20,150,20,'2027-06-30',0,1,2),
('Ibuprofeno 400mg',          'Ibuprofeno',          'Tabletas',    '400mg',      5.80,  2.10, 80,15,'2026-12-31',0,3,1),
('Amoxicilina 500mg',         'Amoxicilina',         'Cápsulas',    '500mg',     12.50,  4.80, 60,10,'2026-09-30',1,2,3),
('Loratadina 10mg',           'Loratadina',          'Tabletas',    '10mg',       4.20,  1.50,120,20,'2027-03-31',0,4,2),
('Vitamina C 1000mg',         'Ácido Ascórbico',     'Tabletas',    '1000mg',     6.90,  2.80,200,30,'2028-01-31',0,5,4),
('Metformina 850mg',          'Metformina HCl',      'Tabletas',    '850mg',      8.40,  3.20, 45,10,'2026-08-31',1,6,1),
('Enalapril 10mg',            'Enalapril Maleato',   'Tabletas',    '10mg',       7.20,  2.90, 55,10,'2027-02-28',1,7,3),
('Omeprazol 20mg',            'Omeprazol',           'Cápsulas',    '20mg',       9.80,  3.60, 90,15,'2026-11-30',0,8,2),
('Clotrimazol Crema 1%',      'Clotrimazol',         'Crema',       '1%',         8.50,  3.20, 40, 8,'2027-05-31',0,9,4),
('Salbutamol Inhalador',      'Salbutamol Sulfato',  'Inhalador',   '100mcg/dosis',24.90,10.50,30, 5,'2026-10-31',1,10,1),
('Diclofenaco 50mg',          'Diclofenaco Sódico',  'Tabletas',    '50mg',       4.90,  1.80,100,15,'2027-04-30',0,3,2),
('Azitromicina 500mg',        'Azitromicina',        'Tabletas',    '500mg',     18.50,  7.20, 35, 8,'2026-07-31',1,2,3),
('Cetirizina 10mg',           'Cetirizina HCl',      'Tabletas',    '10mg',       5.50,  2.00, 95,15,'2027-08-31',0,4,5),
('Vitamina D3 1000UI',        'Colecalciferol',      'Cápsulas',    '1000UI',    14.90,  6.00,110,20,'2028-06-30',0,5,4),
('Insulina Glargina 100UI/ml','Insulina Glargina',   'Inyectable',  '100UI/ml',  85.00, 42.00, 15, 5,'2025-12-31',1,6,1),
('Losartan 50mg',             'Losartan Potásico',   'Tabletas',    '50mg',      10.20,  4.10, 65,12,'2027-01-31',1,7,3),
('Ranitidina 150mg',          'Ranitidina HCl',      'Tabletas',    '150mg',      5.30,  1.90, 75,12,'2026-08-31',0,8,2),
('Pantoprazol 40mg',          'Pantoprazol Sódico',  'Tabletas',    '40mg',      11.50,  4.60, 55,10,'2027-02-28',0,8,2),
('Amlodipino 5mg',            'Amlodipino Besilato', 'Tabletas',    '5mg',        8.90,  3.50, 60,10,'2027-06-30',1,7,3),
('Ambroxol Jarabe 15mg/5ml',  'Ambroxol HCl',        'Jarabe',      '15mg/5ml',   9.20,  3.60, 50,10,'2027-04-30',0,10,2),
('Naproxeno 550mg',           'Naproxeno Sódico',    'Tabletas',    '550mg',      6.40,  2.40, 70,12,'2027-03-31',0,3,5),
('Ciprofloxacino 500mg',      'Ciprofloxacino',      'Tabletas',    '500mg',     14.80,  5.80, 40, 8,'2026-06-30',1,2,3),
('Sulfato Ferroso 300mg',     'Sulfato Ferroso',     'Tabletas',    '300mg',      4.10,  1.40,130,20,'2028-03-31',0,5,4),
('Ketoconazol Crema 2%',      'Ketoconazol',         'Crema',       '2%',        13.80,  5.50, 22, 5,'2026-11-30',0,9,5),
('Calcio + Vitamina D',       'Calcio Carbonato',    'Tabletas',    '600mg+400UI',16.50, 6.80, 85,15,'2028-09-30',0,5,4),
('Dexametasona 4mg/2ml',      'Dexametasona',        'Inyectable',  '4mg/2ml',   12.00,  4.80, 25, 5,'2026-09-30',1,3,1),
('Hidrocortisona Crema 1%',   'Hidrocortisona',      'Crema',       '1%',        11.20,  4.50, 28, 5,'2027-09-30',0,9,4),
('Budesonida Inhalador',      'Budesonida',          'Inhalador',   '200mcg/dosis',38.50,16.00,18, 5,'2026-12-31',1,10,1),
('Paracetamol Jarabe 120mg',  'Paracetamol',         'Jarabe',      '120mg/5ml',  7.90,  3.10, 45,10,'2027-01-31',0,1,2),
('Glibenclamida 5mg',         'Glibenclamida',       'Tabletas',    '5mg',        6.80,  2.50, 50,10,'2026-05-31',1,6,1);

-- Ventas con clientes registrados (y una sin cliente)
INSERT INTO ventas (cliente_id, total_venta, estado, observacion, usuario_id, fecha_venta) VALUES
(1, 25.20, 'COMPLETADA', NULL,                    2, '2025-03-01 09:15:00'),
(2, 36.10, 'COMPLETADA', NULL,                    2, '2025-03-01 10:30:00'),
(3, 24.90, 'COMPLETADA', NULL,                    3, '2025-03-02 08:45:00'),
(4, 23.70, 'COMPLETADA', NULL,                    2, '2025-03-03 11:20:00'),
(5, 85.00, 'ANULADA',    'Devuelto por el cliente',2, '2025-03-03 14:00:00'),
(NULL,12.30,'COMPLETADA','Venta sin cliente registrado',3,'2025-03-04 09:00:00');

INSERT INTO detalle_ventas (venta_id,medicamento_id,cantidad,precio_unitario,subtotal) VALUES
(1,1,2,3.50,7.00),(1,4,2,4.20,8.40),(1,8,1,9.80,9.80),
(2,3,1,12.50,12.50),(2,11,2,4.90,9.80),(2,5,2,6.90,13.80),
(3,10,1,24.90,24.90),
(4,17,1,5.30,5.30),(4,1,1,3.50,3.50),(4,14,1,14.90,14.90),
(5,15,1,85.00,85.00),
(6,1,2,3.50,7.00),(6,4,1,4.20,4.20),(6,8,0,9.80,0.00);

UPDATE ventas SET total_venta=25.20 WHERE id=1;
UPDATE ventas SET total_venta=36.10 WHERE id=2;
UPDATE ventas SET total_venta=24.90 WHERE id=3;
UPDATE ventas SET total_venta=23.70 WHERE id=4;
UPDATE ventas SET total_venta=85.00 WHERE id=5;
UPDATE ventas SET total_venta=11.20 WHERE id=6;

SELECT 'Categorías:' AS tabla,COUNT(*) AS total FROM categorias
UNION ALL SELECT 'Proveedores:',COUNT(*) FROM proveedores
UNION ALL SELECT 'Clientes:',COUNT(*) FROM clientes
UNION ALL SELECT 'Usuarios:',COUNT(*) FROM usuarios
UNION ALL SELECT 'Medicamentos:',COUNT(*) FROM medicamentos
UNION ALL SELECT 'Ventas:',COUNT(*) FROM ventas
UNION ALL SELECT 'Detalle ventas:',COUNT(*) FROM detalle_ventas;

SELECT '✅ bd_farmacia_salud v2 creada correctamente' AS resultado;
