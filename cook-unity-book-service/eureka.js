const Eureka  = require("eureka-js-client").Eureka;

module.exports = function(PORT){
    const client = new Eureka({
        instance:{
            app:"booking-ws",
            hostName: 'localhost',
            instanceId:'book-ws-'+PORT,
            ipAddr:'127.0.0.1',
            statusPageUrl:'http://localhost:'+PORT,
            vipAddress:'booking-ws',
            port :{
                $:PORT,
                '@enabled': 'true'
            },
            dataCenterInfo:{
                '@class':'com.netflix.appinfo.InstanceInfo$DefaultDataCenterInfo',
                name: 'MyOwn'
            },
            registerWithEureka: true,
            fetchReistry: true
        },
        eureka: {
            host:'localhost',
            port:8010,
            servicePath:'/eureka/apps/'
        },
    });
    
    client.logger.level('debug')
    client.start(error => {
        console.log(error || "Booking-Service connected to Eureka Service");
    })
}