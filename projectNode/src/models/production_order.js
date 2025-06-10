const { pool } = require("../db/config");

class orderModels {
  async getAllOrder() {
    const conn = await pool.getConnection();
    try {
      const query = `
        SELECT 
          o.id,
          o.fecha_orden,
          o.fecha_entrega,
          o.cantidad_productos_solicitada,
          o.cantidad_insumo_necesaria,
          o.anotaciones,
          o.estado_orden,
            u.email AS nombre_usuario,
          i.nombre_insumo AS nombre_insumo,
          p.nombre_producto AS nombre_producto
        FROM orden_de_produccion o
        JOIN usuario u ON o.usuario_id = u.id
        JOIN insumos i ON o.insumos_id = i.id
        JOIN productos p ON o.producto_id = p.id
      `;
      const data = await conn.query(query);
      return data;
    } catch (err) {
      throw new Error("Error al obtener órdenes de producción: " + err.message);
    } finally {
      conn.release();
    }
  }
  

  async getOrderById(id) {
    const conn = await pool.getConnection();
    try {
      const query = "SELECT * FROM orden_de_produccion WHERE id = ?";
      const data = await conn.query(query, [id]);
      return data.length > 0 ? data[0] : null;  
    } finally {
      conn.release();
    }
  }

  async addOrder(body) {
    const conn = await pool.getConnection();
    try {
      const query = `
        INSERT INTO orden_de_produccion (
          fecha_entrega, 
          cantidad_productos_solicitada,
          cantidad_insumo_necesaria,
          usuario_id,
          anotaciones, 
          estado_orden, 
          insumos_id, 
          producto_id
        ) VALUES (?, ?, ?, ?, ?, ?, ?, ?)
      `;
      const values = [
        body.fecha_entrega,                
        body.cantidad_productos_solicitada, 
        body.cantidad_insumo_necesaria,     
        body.usuario_id,                   
        body.anotaciones,                   
        body.estado_orden,                  
        body.insumos_id,                   
        body.producto_id                   
      ];
      const result = await conn.query(query, values);
      return result;
    } catch (err) {
      throw new Error("Error al agregar la orden: " + err.message);
    } finally {
      conn.release();
    }
  }

  async updateOrder(body) {
    const conn = await pool.getConnection();
    try {
      const query = `
        UPDATE orden_de_produccion 
        SET 
          fecha_entrega = ?, 
          cantidad_productos_solicitada = ?, 
          cantidad_insumo_necesaria = ?, 
          usuario_id = ?, 
          anotaciones = ?, 
          estado_orden = ?, 
          insumos_id = ?, 
          producto_id = ?
        WHERE id = ?
      `;
      const values = [
        body.fecha_entrega,                  
        body.cantidad_productos_solicitada, 
        body.cantidad_insumo_necesaria,     
        body.usuario_id,                    
        body.anotaciones,                   
        body.estado_orden,                  
        body.insumos_id,                    
        body.producto_id,                   
        body.id                             
      ];
      const result = await conn.query(query, values);
      return result;
    } catch (err) {
      throw new Error("Error al actualizar la orden: " + err.message);
    } finally {
      conn.release();
    }
  }

  async deleteOrder(id) {
    const conn = await pool.getConnection();
    try {
      const query = `
        DELETE FROM orden_de_produccion 
        WHERE id = ?
      `;
      const result = await conn.query(query, [id]);
      return result;
    } catch (err) {
      throw new Error("Error al eliminar la orden: " + err.message);
    } finally {
      conn.release();
    }
  }
}




module.exports = { orderModels };