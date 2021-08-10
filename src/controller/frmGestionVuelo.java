package controller;

import dao.DestinosDAOImpl;
import model.Destino;
import model.Vuelo;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import dao.VueloDAOImpl;

public class frmGestionVuelo extends javax.swing.JFrame {

    DestinosDAOImpl destinosReposi = new DestinosDAOImpl();
    VueloDAOImpl vueloReposi = new VueloDAOImpl();
    int ncompras;

    public frmGestionVuelo() {
      
      
        initComponents();
        this.setLocationRelativeTo(null);
        /*for (Destino lugar : destinosReposi.readAll()) {
            cboOrigen.addItem(lugar.toString());
            cboDestino.addItem(lugar.toString());
        }*/
        Iterator iterator = destinosReposi.readAll().iterator();
        while(iterator.hasNext()) {
          Object element = iterator.next();
          cboOrigen.addItem(element.toString());
            cboDestino.addItem(element.toString());
        } 
        
        mostrarListaVuelos();
        habilitarCamposTexto();
        inicializar();
    }

    public void inicializar() {
        btnEditar.setEnabled(false);
        btnEliminar.setEnabled(false);
    }

    public void limpiarCamposTexto() {
        txtCodigo.setText("");
        txtPrecio.setText("");
        txtCapacidad.setText("");
        cboOrigen.setSelectedItem(1);
        cboDestino.setSelectedItem(1);
        txtPrecio.setText("");
        txtHPartida.setText("");
        txtHLlegada.setText("");
    }

    public void habilitarCamposTexto() {
        txtCodigo.setEnabled(true);
        txtPrecio.setEnabled(true);
        txtCapacidad.setEnabled(true);
        cboOrigen.setEnabled(true);
        cboDestino.setEnabled(true);
        txtPrecio.setEnabled(true);
        txtHPartida.setEnabled(true);
        txtHLlegada.setEnabled(true);
    }

    private void mostrarListaVuelos(List<Vuelo> lista) {
        DefaultTableModel dtm = (DefaultTableModel) tDatos.getModel();
        dtm.setRowCount(0);

        for (Vuelo vuelo : lista) {
            /*Object[] fila = new Object[4];
            fila[0] = producto.getCodigo();
            fila[1] = producto.getDescripcion();
            fila[2] = producto.getDatosFabricacion();
            fila[3] = producto.getPrecio();
             */
            //Manera 2
            Object[] fila = new Object[]{
                vuelo.getCodigo(),
                vuelo.getOrigen(),
                vuelo.getDestino(),
                vuelo.getCapacidad(),
                vuelo.getVacante(),
                vuelo.getHpartida(),
                vuelo.getHllegada(),
                vuelo.getPrecio()
            };
            dtm.addRow(fila);
        }

    }

    private void mostrarListaVuelos() {
        mostrarListaVuelos(vueloReposi.readAll());
    }

    public boolean verificarLlenadoCampos() {
        boolean vacio;
        vacio = (txtCodigo.getText().equals("")) || (txtPrecio.getText().equals("")) || (txtCapacidad.getText().equals(""))
                || txtPrecio.getText().equals("") || txtHPartida.getText().equals("") || txtHLlegada.getText().equals("");

        return !vacio;
    }

    private Vuelo leerVuelo() {
        //Lectura de valors
        if (cboOrigen.getSelectedItem() != cboDestino.getSelectedItem()) {
            if (txtCapacidad.getText() != "0") {
                Vuelo modificado = new Vuelo();
                modificado.setCodigo(Integer.parseInt(txtCodigo.getText()));
                modificado.setOrigen(cboOrigen.getSelectedItem().toString());
                modificado.setDestino(cboDestino.getSelectedItem().toString());
                modificado.setCapacidad(Integer.parseInt(txtCapacidad.getText()));
                modificado.setVacante(Integer.parseInt(txtCapacidad.getText()) - modificado.getVacante());
                modificado.setHpartida(txtHPartida.getText());
                modificado.setHllegada(txtHLlegada.getText());
                modificado.setPrecio(Float.parseFloat(txtPrecio.getText()));
                return modificado;
            } else {
                JOptionPane.showMessageDialog(null, "No puede haber una capacidad Nula");
                return null;
            }

        } else {
            JOptionPane.showMessageDialog(null, "No puede ser el mismo origen que el el destino");
            return null;
        }

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btnGrupoCarrera = new javax.swing.ButtonGroup();
        jLabel1 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        txtCodigo = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        txtPrecio = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        btnEditar = new javax.swing.JButton();
        btnVerLista = new javax.swing.JButton();
        btnRegistrar = new javax.swing.JButton();
        txtCapacidad = new javax.swing.JTextField();
        txtHPartida = new javax.swing.JTextField();
        txtHLlegada = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        cboOrigen = new javax.swing.JComboBox();
        cboDestino = new javax.swing.JComboBox<>();
        jPanel2 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        txtBuscar = new javax.swing.JTextField();
        btnBuscar = new javax.swing.JButton();
        btnEliminar = new javax.swing.JButton();
        btnSalir = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tDatos = new javax.swing.JTable();

        setTitle("Gestion de Vuelos");

        jPanel1.setBackground(new java.awt.Color(255, 153, 153));
        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Datos del Vuelo", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Comic Sans MS", 0, 14))); // NOI18N

        jLabel2.setFont(new java.awt.Font("Comic Sans MS", 0, 14)); // NOI18N
        jLabel2.setText("Hora Partida");

        jLabel3.setFont(new java.awt.Font("Comic Sans MS", 0, 14)); // NOI18N
        jLabel3.setText("Precio");

        jLabel4.setFont(new java.awt.Font("Comic Sans MS", 0, 14)); // NOI18N
        jLabel4.setText("Capacidad");

        txtCodigo.setFont(new java.awt.Font("Comic Sans MS", 0, 14)); // NOI18N
        txtCodigo.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtCodigoKeyTyped(evt);
            }
        });

        jLabel6.setFont(new java.awt.Font("Comic Sans MS", 0, 14)); // NOI18N
        jLabel6.setText("Código");

        txtPrecio.setFont(new java.awt.Font("Comic Sans MS", 0, 14)); // NOI18N
        txtPrecio.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtPrecioKeyTyped(evt);
            }
        });

        jLabel7.setFont(new java.awt.Font("Comic Sans MS", 0, 14)); // NOI18N
        jLabel7.setText("Origen");

        jLabel8.setFont(new java.awt.Font("Comic Sans MS", 0, 14)); // NOI18N
        jLabel8.setText("Destino");

        btnEditar.setFont(new java.awt.Font("Comic Sans MS", 0, 14)); // NOI18N
        btnEditar.setText("Editar");
        btnEditar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditarActionPerformed(evt);
            }
        });

        btnVerLista.setFont(new java.awt.Font("Comic Sans MS", 0, 14)); // NOI18N
        btnVerLista.setText("Ver Lista");
        btnVerLista.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnVerListaActionPerformed(evt);
            }
        });

        btnRegistrar.setFont(new java.awt.Font("Comic Sans MS", 0, 14)); // NOI18N
        btnRegistrar.setText("Registrar");
        btnRegistrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRegistrarActionPerformed(evt);
            }
        });

        txtCapacidad.setFont(new java.awt.Font("Comic Sans MS", 0, 14)); // NOI18N
        txtCapacidad.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtCapacidadKeyTyped(evt);
            }
        });

        txtHPartida.setFont(new java.awt.Font("Comic Sans MS", 0, 14)); // NOI18N
        txtHPartida.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtHPartidaKeyTyped(evt);
            }
        });

        txtHLlegada.setFont(new java.awt.Font("Comic Sans MS", 0, 14)); // NOI18N
        txtHLlegada.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtHLlegadaActionPerformed(evt);
            }
        });
        txtHLlegada.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtHLlegadaKeyTyped(evt);
            }
        });

        jLabel10.setFont(new java.awt.Font("Comic Sans MS", 0, 14)); // NOI18N
        jLabel10.setText("Hora Llegada");

        cboOrigen.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboOrigenActionPerformed(evt);
            }
        });

        cboDestino.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboDestinoActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(32, 32, 32)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel7)
                                    .addComponent(jLabel8)
                                    .addComponent(jLabel3)
                                    .addComponent(jLabel2)
                                    .addComponent(jLabel4)
                                    .addComponent(jLabel10, javax.swing.GroupLayout.Alignment.TRAILING))
                                .addGap(18, 18, 18))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel6)
                                .addGap(62, 62, 62)))
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtPrecio, javax.swing.GroupLayout.PREFERRED_SIZE, 173, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, 173, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(12, 12, 12)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(cboOrigen, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(cboDestino, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(txtHLlegada, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 69, Short.MAX_VALUE)
                                .addComponent(txtHPartida, javax.swing.GroupLayout.Alignment.LEADING))
                            .addComponent(txtCapacidad, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(btnEditar)
                        .addGap(18, 18, 18)
                        .addComponent(btnRegistrar)
                        .addGap(18, 18, 18)
                        .addComponent(btnVerLista)))
                .addGap(0, 36, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(40, 40, 40)
                        .addComponent(txtCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel6)))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(txtCapacidad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(cboOrigen, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(22, 22, 22)
                        .addComponent(jLabel8))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cboDestino, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(txtPrecio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txtHPartida, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(14, 14, 14)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtHLlegada, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel10))
                .addGap(36, 36, 36)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnEditar)
                    .addComponent(btnRegistrar)
                    .addComponent(btnVerLista))
                .addContainerGap(42, Short.MAX_VALUE))
        );

        jPanel2.setBackground(new java.awt.Color(204, 204, 255));
        jPanel2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel5.setFont(new java.awt.Font("Comic Sans MS", 0, 14)); // NOI18N
        jLabel5.setText("Buscar por código");

        txtBuscar.setFont(new java.awt.Font("Comic Sans MS", 0, 14)); // NOI18N
        txtBuscar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtBuscarKeyTyped(evt);
            }
        });

        btnBuscar.setFont(new java.awt.Font("Comic Sans MS", 0, 14)); // NOI18N
        btnBuscar.setText("Buscar");
        btnBuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarActionPerformed(evt);
            }
        });

        btnEliminar.setFont(new java.awt.Font("Comic Sans MS", 0, 14)); // NOI18N
        btnEliminar.setText("Eliminar");
        btnEliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarActionPerformed(evt);
            }
        });

        btnSalir.setFont(new java.awt.Font("Comic Sans MS", 0, 14)); // NOI18N
        btnSalir.setText("Salir");
        btnSalir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSalirActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel5)
                .addGap(18, 18, 18)
                .addComponent(txtBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 168, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 125, Short.MAX_VALUE)
                .addComponent(btnBuscar)
                .addGap(58, 58, 58)
                .addComponent(btnEliminar)
                .addGap(65, 65, 65)
                .addComponent(btnSalir)
                .addGap(23, 23, 23))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel5)
                        .addComponent(txtBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btnSalir)
                        .addComponent(btnEliminar)
                        .addComponent(btnBuscar)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        tDatos.setFont(new java.awt.Font("Comic Sans MS", 0, 14)); // NOI18N
        tDatos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Codigo", "Origen", "Destino", "Capacidad", "Vacantes", "H. Salida", "H. Llegada"
            }
        ));
        tDatos.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tDatosMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tDatos);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(29, 29, 29)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel1)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(95, 95, 95)
                        .addComponent(jLabel1))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(30, 30, 30)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 352, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(29, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnVerListaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnVerListaActionPerformed
        limpiarCamposTexto();
        mostrarListaVuelos();
    }//GEN-LAST:event_btnVerListaActionPerformed

    private void btnEditarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditarActionPerformed
        Vuelo vueloNuevo = leerVuelo();

        if (vueloNuevo != null) {
            //Enviar al servicio
            if (vueloReposi.updateN(vueloNuevo, ncompras)) {
                JOptionPane.showMessageDialog(null, "Modificacion correcta...");
                btnEditar.setEnabled(false);
            }
        } else {
            JOptionPane.showMessageDialog(null, "No se pudo concretar");
        }
        mostrarListaVuelos();
        limpiarCamposTexto();
    }//GEN-LAST:event_btnEditarActionPerformed

    private void btnBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarActionPerformed

        Vuelo ad = vueloReposi.read(Integer.parseInt(txtBuscar.getText()));
        if (ad != null) { //Si ha sido encontrado
            habilitarCamposTexto();
            txtBuscar.setText("");

            txtCodigo.setText(String.valueOf(ad.getCodigo()));
            cboOrigen.setSelectedItem(ad.getOrigen());
            cboDestino.setSelectedItem(ad.getDestino());
            txtCapacidad.setText(String.valueOf(ad.getCapacidad()));
            txtPrecio.setText(String.valueOf(ad.getPrecio()));
            txtHPartida.setText(ad.getHpartida());
            txtHLlegada.setText(ad.getHllegada());

            ArrayList<Vuelo> listaVuelos = new ArrayList<>();
            listaVuelos.add(ad);
            ncompras = ad.getCapacidad() - ad.getVacante();
            mostrarListaVuelos(listaVuelos);
            btnEditar.setEnabled(true);
            btnEliminar.setEnabled(true);
        } else {
            JOptionPane.showMessageDialog(null, "No ha sido encontrado...");
        }
        habilitarCamposTexto();
    }//GEN-LAST:event_btnBuscarActionPerformed

    private void btnEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarActionPerformed
        int codigo = Integer.parseInt(txtBuscar.getText());
        //Enviar al servicio
        if (vueloReposi.deleteC(codigo)) {
            JOptionPane.showMessageDialog(null, "Eliminacion correcta...");
            btnEditar.setEnabled(false);
        } else {
            JOptionPane.showMessageDialog(null, "No se pudo concretar");
        }
        mostrarListaVuelos();
        limpiarCamposTexto();
    }//GEN-LAST:event_btnEliminarActionPerformed

    private void btnSalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalirActionPerformed
        this.dispose();
    }//GEN-LAST:event_btnSalirActionPerformed

    private void btnRegistrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRegistrarActionPerformed
        Vuelo vueloNuevo = leerVuelo();
        //Enviar al servicio

        if (vueloNuevo != null) {
            //Enviar al servicio
            if (vueloReposi.create(vueloNuevo)) {
                JOptionPane.showMessageDialog(null, "Registro correcto...");
                btnEditar.setEnabled(false);
            } else {
                JOptionPane.showMessageDialog(null, "No se pudo concretar");
            }
        }
        mostrarListaVuelos();
        limpiarCamposTexto();
    }//GEN-LAST:event_btnRegistrarActionPerformed

    private void txtPrecioKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPrecioKeyTyped
        char c = evt.getKeyChar();
        if ((c < '0' || c > '9') || txtPrecio.getText().length() > 4) {
            evt.consume();
        }
    }//GEN-LAST:event_txtPrecioKeyTyped

    private void tDatosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tDatosMouseClicked

    }//GEN-LAST:event_tDatosMouseClicked

    private void txtCapacidadKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCapacidadKeyTyped
        char c = evt.getKeyChar();
        if ((c < '0' || c > '9') || txtCapacidad.getText().length() > 2) {
            evt.consume();
        }
    }//GEN-LAST:event_txtCapacidadKeyTyped

    private void txtHPartidaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtHPartidaKeyTyped

        if (txtHPartida.getText().length() > 4) {
            evt.consume();
        }
    }//GEN-LAST:event_txtHPartidaKeyTyped

    private void txtHLlegadaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtHLlegadaKeyTyped

        if (txtHLlegada.getText().length() > 4) {
            evt.consume();
        }
    }//GEN-LAST:event_txtHLlegadaKeyTyped

    private void txtHLlegadaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtHLlegadaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtHLlegadaActionPerformed

    private void cboDestinoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboDestinoActionPerformed

    }//GEN-LAST:event_cboDestinoActionPerformed

    private void cboOrigenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboOrigenActionPerformed
        // TODO add your handling code here:


    }//GEN-LAST:event_cboOrigenActionPerformed

    private void txtCodigoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCodigoKeyTyped
        char c = evt.getKeyChar();
        if ((c < '0' || c > '9') || txtCodigo.getText().length() > 5) {
            evt.consume();
        }
    }//GEN-LAST:event_txtCodigoKeyTyped

    private void txtBuscarKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBuscarKeyTyped
        char c = evt.getKeyChar();
        if ((c < '0' || c > '9') || txtCodigo.getText().length() > 5) {
            evt.consume();
        }
    }//GEN-LAST:event_txtBuscarKeyTyped

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;

                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(frmGestionVuelo.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(frmGestionVuelo.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(frmGestionVuelo.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(frmGestionVuelo.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new frmGestionVuelo().setVisible(true);

            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBuscar;
    private javax.swing.JButton btnEditar;
    private javax.swing.JButton btnEliminar;
    private javax.swing.ButtonGroup btnGrupoCarrera;
    private javax.swing.JButton btnRegistrar;
    private javax.swing.JButton btnSalir;
    private javax.swing.JButton btnVerLista;
    private javax.swing.JComboBox<String> cboDestino;
    private javax.swing.JComboBox<String> cboOrigen;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tDatos;
    private javax.swing.JTextField txtBuscar;
    private javax.swing.JTextField txtCapacidad;
    private javax.swing.JTextField txtCodigo;
    private javax.swing.JTextField txtHLlegada;
    private javax.swing.JTextField txtHPartida;
    private javax.swing.JTextField txtPrecio;
    // End of variables declaration//GEN-END:variables
}
