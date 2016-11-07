package week8.aop;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;


@Aspect
public class MyAspect {

    //@Before("execution(public * *(..))")
    public void authorServiceAdvice(){
        System.out.println("Before service advice" );
    }

    //@Around("execution(public * *(..))")
    public Object authoreAdvice(ProceedingJoinPoint joinPoint){

        Signature signature  = joinPoint.getSignature();
        System.out.println(signature.toLongString());

        try {
            return joinPoint.proceed(); //call method
        } catch (Throwable throwable){
            throwable.printStackTrace();
        }
        System.out.println("Before service advice" );

        return null;
    }

    @Around("execution(public * classwork.service.AuthorServiceImpl.getBooks(int))")
    public Object getBooksAdvice(ProceedingJoinPoint joinPoint){

        Signature signature  = joinPoint.getSignature();
        System.out.println(signature.toLongString());

        Integer arg1 = (Integer) joinPoint.getArgs()[0];
        if(arg1 < 0){
            return null;
        }
        try {
            return joinPoint.proceed(); //call method
        } catch (Throwable throwable){
            throwable.printStackTrace();
        }
        System.out.println("Before service advice" );

        return null;
    }
}
