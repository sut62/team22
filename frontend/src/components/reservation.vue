<template>
  <v-container class="mt-12 ml-12" style="background-color: white;">
    <h2>จองโต๊ะอาหาร</h2>
    <v-text-field 
      v-model.number="reservation.customer"      
      :label="show"  
      prepend-icon="mdi-human-male-female"
      :rules="[(v) => !!v || 'Item is required']"
      required
    ></v-text-field>
		<v-btn color="success"
      @click="customerchk"
    >Search
    <v-icon>mdi-magnify</v-icon>
    </v-btn>
    

		<v-menu max-width="290" >
      <template v-slot:activator="{ on }">
        <v-text-field
        
        readonly
        label="Date"
        prepend-icon="mdi-calendar-range"
        :value="formattedDate"
        v-on="on"
        :rules="[(v) => !!v || 'Item is required']"
        required
      ></v-text-field>
      </template>
      <v-date-picker v-model="date" ></v-date-picker>
    </v-menu>
    <v-row class="ml-0">
        <v-menu
         
          v-model="menu1"
          :close-on-content-click="false"
          :nudge-right="40"
          lazy
          transition="scale-transition"
          offset-y
          max-width="290x"
          max-height="290x"
        >
          <template v-slot:activator="{on}"
          >
            <v-text-field
              v-model="reservation.time"
              v-on="on"
              label="Pick Time"
              prepend-icon="access_time"
              readlonly
              id="id"
              :rules="[(v) => !!v || 'Item is required']"
              required
            ></v-text-field>
            
          </template>
          <v-time-picker
              v-if="menu1"
              v-model="reservation.time"
              full-width
              format="24hr"
              :allowed-minutes="allowmins"
              min="9:00"
              max="21:00"
            >
            <v-spacer></v-spacer>
            <v-btn text color="primary" @click="menu1 = false">Cancel</v-btn>
            <v-btn text color="primary" @click="menu1 = false">OK</v-btn>
            </v-time-picker>
        </v-menu>
     </v-row>
    <v-select

      label="Select Table"
      v-model="reservation.table"
      :items="table"
      item-text="id"
      item-value="id"
      prepend-icon="mdi-table-chair"
      return-object
      :rules="[(v) => !!v || 'Item is required']"
      required
      
    >
    </v-select>
    <v-select
      
      label="Select Number of Seats"
      v-model="reservation.seats"
      :items="seat"
      item-text="id"
      item-value="id"
      prepend-icon="mdi-seat"
      :rules="[(v) => !!v || 'Item is required']"
      required
      return-object
    >
    </v-select>
    <v-select
            
      :items="services"
      v-model="reservation.service"
      label="Select Service"
      item-text="serviceName"
      item-value="id"
      prepend-icon="mdi-room-service"
      :rules="[(v) => !!v || 'Item is required']"
      required
      return-object
    ></v-select>
    
    <v-btn color="success" class="mx-2"
      @click="getReservations"
      
    >RESERVATION CHECK 
    <v-icon>mdi-check</v-icon>
    </v-btn>
    <v-btn v-if="showsave" color="success"
      @click="save"
      
    >SAVE 
    <v-icon>mdi-floppy</v-icon>
    
    </v-btn>
    <img  ma-5 :src="imge">
     <v-alert class="my-2" v-if="findmember == 'found'" color="green">Member Found</v-alert>
     <v-alert class="my-2" v-else-if="findmember == 'notfound'" type="error">Member Not Found</v-alert>
     
    <v-alert class="my-2" v-if="dupe" type="error">This time and date is already reserved</v-alert>
     <v-alert class="my-2" v-if=" savechk == 'save'" color="green">{{errmsg}}</v-alert>
     <v-alert class="my-2" v-else-if="savechk == 'notsave'" type="error">{{errmsg}}</v-alert>
     
  </v-container>
</template>

<script>
import format from 'date-fns/format'
import parselISO from 'date-fns/parseISO'
import https from '../plugins/https'
export default {
  name: 'reservation',

  data: () => ({
        menu1:false,
        date:null,
        service:null,
        reservation:{
          time:null,
          table:null,
          seats:null,
          customer:null,
          service:null,
        },
        reservations:[],
        services:[],
        findmember:'',
        table:[],
        seat:[],
        show:"Member ID",
        savechk:'',
        errmsg:``,
        dupe:false,
        showsave:false,
        imge:null,
        openimg:false,
        memr:null
  }),
  computed:{
    formattedDate(){
      return this.date ? format(parselISO(this.date), 'do MMM YYY') : ''
    },
    seats(){
      return this.reservation.table
    },
    
    
    
  },
  methods:{
    allowmins : m => m%30 === 0 ,
    customerchk(){
      https.get("/members/"+this.reservation.tel).then( doc =>{
              if(doc.data!=null){
               this.findmember='found'
              }
              else{
                this.findmember='notfound'
              }
      })
      .catch(()=> {
            this.findmember='notfound'
      })
    },
  
    save(){
        if(!this.dupe){
          https.post("/reservationses/"+this.reservation.customer+"/"+this.reservation.table.id+"/"+this.reservation.service.id+"/"+this.date+"/"+this.reservation.time+"/"+this.reservation.seats.id)
        .then(doc => {
           this.errmsg=`The reservation is saved status : ${doc.status}`
           this.savechk='save'
           this.getImg()
        })
        .catch(e =>{
            this.errmsg=`The reservation is not saved : ${e.message}`
            this.savechk='notsave'
        })
        }
        else{
          this.errmsg=`Your data is duplicated!`
          this.savechk='notsave'
        }
       
    },
    getTables(){
       https.get("/tableses").then( doc =>{
        this.table=doc.data
        
          
      })
      .catch(e =>{
          alert(e.name+" "+e.message)
          
      })
    },
    getService(){
        https.get("/serviceses").then( doc =>{
              
                      
        this.services=doc.data
               
                
      })
      .catch( e =>{
        alert(e.name+" "+e.message)
      })
    },
    getReservations(){
      https.get("/reservationses/"+this.reservation.table.id+"/"+this.date+"/"+this.reservation.time).then( doc => {
        this.dupe=doc.data
        this.showsave=!doc.data
      })
      .catch(e => {
        alert(e.name+" "+e.message)
      })
    },
    getImg(){
      var text = "Table: "+this.reservation.table.id+" Number of Seats: "+this.reservation.seats.id+" "+"Reservation Date : "+this.date+" "+this.reservation.time+" "+"Service: "+this.reservation.service.serviceName
      https.get("/image/"+text).then( doc => {
        console.log(doc.data.content)
        this.imge = "http://localhost:9000/image/Table:%20"+this.reservation.table.id+"%20Number%20of%20Seats:%20"+this.reservation.seats.id+"%20Reservation%20Date%20:%20"+this.date+"%20"+this.reservation.time+"%20Service:%20"+this.reservation.service.serviceName
        
      }

      )
    }
    
  },
  mounted(){
    this.getTables()
    this.getService()
   
  },
  watch:{
    seats(){
      let mxSeat = this.reservation.table.seats
      for(let nseat = 1;nseat <= mxSeat;nseat++){
        this.seat.push({id:nseat})
      }
    },
    
  }  
}
</script>
