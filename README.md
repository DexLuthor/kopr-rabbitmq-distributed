# KOPR project with RabbitMQ

#### Paytel s.r.o. manufactures a system for peer-to-peer mobile payments, ie a fast mechanism for transferring money between mobile phones.Each participant has own mobile phone with a credit that they can use to pay to another participant.
---

## Technical requirements:
- **payment to another participant**<br/>
   The payer sends to the other telephone number any amount and the purpose of the payment. The sender and the date and time of payment are recorded
- **credit charging**<br/>
   The participant's credit can be charged with credit from the external system. The date-time, the amount and the location where the credit was charged are recorded.
- **suspicious payments**<br/>
   The payment is suspicious if it is over 5000 euros. Record such payments immediately in a convenient manner so that in the future it is easy to make a notification via SMS, e-mail, etc. Count on the fact that monitoring suspicious payments is independent of the actual delivery of money.
---
## Used technical stack:
 - JDK 15
 - Lombok
 - Spring Data MongoDB
 - Spring for RabbitMQ

![updated](https://user-images.githubusercontent.com/53663457/99878739-bbacf680-2c07-11eb-82b9-ea779c150ef8.png)

---
## How to run

In Intellij IDEA Ultimate Edition:
1. Clone repo
   1. File -> New -> Project from Version Control
   2. URL: https://github.com/DexLuthor/kopr-rabbit-distributed.git
   3. Clone
2. Let Maven download all necessary dependencies
   1. Press `Double Shift` -> Reload All Maven Projects
3. Run Configurations
   1. Run -> Edit configurations
   2. Button 'plus' (add new configuration)
   3. Spring Boot
   4. Main class: `com.github.dexluthor.RabbitConsumerApplication`
   5. In the '*override parameters*' section add parameter: name - '*phoneNumber*', value - any you want
   6. Apply
