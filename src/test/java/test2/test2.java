package test2;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class test2 {

    @Test
    public void testGetD(){ //проверяем функцию вычисления дискриминанта (положительное число)
        MathService mathService = new MathService();
        int result = mathService.getD(1,5,3);
        assertEquals(13,result);
    }

    @Test
    public void testPair(){//проверка метода Pair
        Pair pair = new Pair(1.0,5.0);
        String res = pair.toString();
        assertEquals("Answer{first=1.0, second=5.0}", res );

    }

    @Test
    public void testgetAnswerDLessThanZero() {//дискриминант меньше 0
        MathService mathService = new MathService();
          assertThatThrownBy(() ->
                mathService.getAnswer(1, 2, 3)
       ).isInstanceOf(NotFoundAnswerException.class);
    }

    @Test
    public void testAnswerDequalToZero() throws NotFoundAnswerException {//дискриминант равен 0
        MathService mathService = new MathService();
        Pair result = mathService.getAnswer(1,2,1);
        Pair pair = new Pair(result.first,result.second);
        String res = pair.toString();

        assertEquals("Answer{first=-1.0, second=-1.0}" ,res);
    }

    @Test
    public void testAnswerDGreaterThanZero() throws NotFoundAnswerException {//дискриминант больше 0
        MathService mathService = new MathService();
        Pair result = mathService.getAnswer(1,5,3);
        System.out.println(result);
        Pair pair = new Pair(result.first,result.second);
        String res = pair.toString();

        assertEquals("Answer{first=-0.6972243622680054, second=-4.302775637731995}",res);
    }

    @ParameterizedTest
    @CsvSource({"1,2,3","4,5,6","7,8,9"}) //дискриминант меньше 0 параметризованный
    public void testParam (int x, int y, int z)  {
        MathService mathService = new MathService();
        assertThatThrownBy(() ->
                mathService.getAnswer(x, y, z)
        ).isInstanceOf(NotFoundAnswerException.class);

    }

    @Test
    public void testOne() throws NotFoundAnswerException { //все проверки
        MathService mathService = new MathService();

        assertThat(mathService.getAnswer(1,2,1).toString()).isEqualTo("Answer{first=-1.0, second=-1.0}");
        assertThat(mathService.getAnswer(1,5,3).toString()).isEqualTo("Answer{first=-0.6972243622680054, second=-4.302775637731995}");
        assertThatThrownBy(() -> mathService.getAnswer(1, 2, 3)).isInstanceOf(NotFoundAnswerException.class);

}}
