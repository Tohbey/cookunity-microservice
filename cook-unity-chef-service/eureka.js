const Eureka  = require("eureka-js-client").Eureka;

module.exports = function(PORT){
    const client = new Eureka({
        instance:{
            app:"cook-unit-chef-service",
            hostName: 'localhost',
            instanceId:'cook-unit-chef-service-'+PORT,
            ipAddr:'127.0.0.1',
            statusPageUrl:'http://localhost:3000',
            vipAddress:'cook-unit-chef-service',
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
        console.log(error || "Nodejs Eureka Started");
    })
}