<template>
  <div class="mainpage">
    <div class="outside">
      <br>
      <v-col cols = 12>
        <v-container>
          <v-layout row wrap>
            <v-col cols = 2>

              <v-text-field
              v-model="ManageMenu.MENU_NAME"
              label="Menu Name"
              :rules="[(v) => !!v || 'Item is required']"
              required
              outlined
              dense
              ></v-text-field>

              <v-text-field
              v-model="ManageMenu.MENU_PRICE"
              type="number"
              :rules="[(v) => !!v || 'Item is required']"
              label="Menu Price"
              required
              outlined
              dense
              ></v-text-field>

              <br>
              <v-select
              v-model="ManageMenu.cid"
              :items="MenuCate"
              label="Menu Category"
              item-text="cname"
              item-value="cid"
              outlined
              dense
              ></v-select>

              <br>
              <v-select
              v-model="ManageMenu.id"
              :items="MenuIngre"
              label="Menu Ingredient"
              item-text="iname"
              item-value="id"
              outlined
              dense
              ></v-select>

              <br>
              <v-select
              v-model="ManageMenu.tid"
              :items="MenuType"
              label="Menu Type"
              item-text="tname"
              item-value="tid"
              outlined
              dense
              ></v-select>

              <br>
              <v-btn small @click="saveData">Add Menu</v-btn>
              <!-- @click="saveData" -->
            </v-col>
            <v-col cols = 8>
              <v-card
              class="mx-auto"
              max-width="850"
              outlined
              >
              <v-simple-table
              fixed-header>
                <template v-slot:default>
                  <thead>
                    <tr>
                      <th class="text-left">Menu name</th>
                      <th class="text-left">Category</th>
                      <th class="text-left">Ingredient</th>
                      <th class="text-left">Type</th>
                      <th class="text-left">Price</th>
                    </tr>
                  </thead>
                  <tbody>
                    <tr v-for="item in showtable" :key="item.name">
                      <td class="text-left">{{ item.m_name }}</td>
                      <td class="text-left">{{ item.sel_cate.cname }}</td>
                      <td class="text-left">{{ item.sel_ingre.iname }}</td>
                      <td class="text-left">{{ item.sel_type.tname }}</td>
                      <td class="text-left">{{ item.m_price+" .-" }}</td>
                    </tr>
                  </tbody>
                </template>
              </v-simple-table>
            </v-card>
          </v-col>
        </v-layout>
      </v-container>
    </v-col>
  </div>
</div>
</template>

<script>
import http from "../https";
export default {
  name:"ManageMenu",
  data(){
    return{
      ManageMenu:{
        cid:"",
        id:"",
        tid:"",
      },
      MENU_NAME:"",
      MENU_PRICE:"",
      MenuCate:[],
      MenuIngre:[],
      MenuType:[],
      showtable:[]
    };
  },
  methods:{
    saveData(){
      http.post("/manageMenus/"+
      this.ManageMenu.MENU_NAME
      +"/"+
      this.ManageMenu.MENU_PRICE
      +"/"+
      this.ManageMenu.cid
      +"/"+
      this.ManageMenu.id
      +"/"+
      this.ManageMenu.tid)
      .then(response => {
        console.log(response);
        alert("เพิ่มเมนูสำเร็จ")
        location.reload();
        this.getDataTable();

      })
      .catch(e => {
        console.log(e);
        alert("เพิ่มเมนูไม่สำเร็จ")
      })
      ;
      this.submitted = true;
    },

    getMenuCate(){

      http.get("/menuCates").then(response => {
        this.MenuCate = response.data;
      });
      this.submitted = true;
    },
    getMenuIngre(){

      http.get("/menuIngres").then(response =>{
        this.MenuIngre = response.data;
      });
      this.submitted = true;
    },
    getMenuType(){
      http.get("/menuTypes").then(response =>{
        this.MenuType = response.data;
      });
      this.submitted = true;

    },
    getDataTable(){
      http.get("/manageMenus").then(response =>{
        this.showtable = response.data;
      });
      this.submitted = true;
    }
  },
  mounted(){
    this.getMenuCate();
    this.getMenuIngre();
    this.getMenuType();
    this.getDataTable();
  }
}
</script>
