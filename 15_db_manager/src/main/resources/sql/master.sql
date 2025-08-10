USE MT_POC;

CREATE TABLE tipos_de_datos_ejemplo (
    id INT PRIMARY KEY,

    -- Tipos numéricos enteros
    tiny_int TINYINT,
    small_int SMALLINT,
    medium_int MEDIUMINT,
    normal_int INT,
    big_int BIGINT,

    -- Tipos numéricos decimales y flotantes
    decimal_num DECIMAL(10,2),
    float_num FLOAT,
    double_num DOUBLE,

    -- Tipos de fecha y hora
    fecha DATE,
    hora TIME,
    fecha_hora DATETIME,
    timestamp_val TIMESTAMP,

    -- Tipo de cadena de texto
    char_val CHAR(10),
    varchar_val VARCHAR(50),
    text_val TEXT,
    tinytext_val TINYTEXT,
    mediumtext_val MEDIUMTEXT,
    longtext_val LONGTEXT,

    -- Tipos binarios
    binary_val BINARY(16),
    varbinary_val VARBINARY(32),
--    blob_val BLOB,
--    tinyblob_val TINYBLOB,
--    mediumblob_val MEDIUMBLOB,
--    longblob_val LONGBLOB,

    -- Tipo enum y set
--    enum_val ENUM('valor1', 'valor2', 'valor3'),
--    set_val SET('uno', 'dos', 'tres'),

    -- Tipos booleanos (en MariaDB es alias de TINYINT(1))
    bool_val BOOLEAN
);
