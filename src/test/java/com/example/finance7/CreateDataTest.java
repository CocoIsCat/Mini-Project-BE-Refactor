package com.example.finance7;

import com.example.finance7.member.entity.Member;
import com.example.finance7.member.entity.Scession;
import com.example.finance7.member.repository.MemberRepository;
import com.example.finance7.product.entity.Card;
import com.example.finance7.product.entity.Loan;
import com.example.finance7.product.entity.Savings;
import com.example.finance7.product.entity.Subscription;
import com.example.finance7.product.repository.CardRepository;
import com.example.finance7.product.repository.LoanRepository;
import com.example.finance7.product.repository.SavingsRepository;
import com.example.finance7.product.repository.SubscriptionRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

@SpringBootTest
class CreateDataTest {

    @Autowired
    CardRepository cardRepository;
    @Autowired
    SavingsRepository savingsRepository;
    @Autowired
    SubscriptionRepository subscriptionRepository;
    @Autowired
    LoanRepository loanRepository;
    @Autowired
    MemberRepository memberRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Test
    void contextLoads() {

        List<Card> cards = new ArrayList<>();
        //Card
        Card card1 = Card.builder()
                .productName("신한카드 EVerywhere")
                .companyName("신한카드")
                .thumbnail("https://www.shinhancard.com/pconts/images/contents/card/plate/cdCreditBTDD41.png")
                .productURL("https://www.shinhancard.com/pconts/html/card/apply/credit/1219683_2207.html")
                .tags("자차\\n" +
                        "성인")
                .annualFee(22000)
                .benefits("전기차 충전요금 20~40% 캐시백\\n" +
                        "생활 가맹점 5~20% 캐시백\\n" +
                        "주차앱 5천원 캐시백")
                .build();
        cards.add(card1);
        Card card2 = Card.builder()
                .productName("신한카드 플리")
                .companyName("신한카드")
                .thumbnail("https://www.shinhancard.com/pconts/images/contents/card/plate/cdCreditPLAD26.png")
                .productURL("https://www.shinhancard.com/pconts/html/card/apply/credit/1216829_2207.html")
                .tags("문화\\n" +
                        "생활")
                .annualFee(18000)
                .benefits("Daily 국내 이용금액 최대 0.9% 결제일 할인\\n" +
                        "Weekly 할인 쿠폰 매주 할인 쿠폰(월4회)\\n" +
                        "Monthly 단골 적립 월 최대 3,000P 적립")
                .build();
        cards.add(card2);
        Card card3 = Card.builder()
                .productName("신한카드 핏(Fit)")
                .companyName("신한카드")
                .thumbnail("https://www.shinhancard.com/pconts/images/contents/card/plate/cdCreditAXKD3X.png")
                .productURL("https://www.shinhancard.com/pconts/html/card/apply/credit/1218726_2207.html")
                .tags("쇼핑")
                .annualFee(18000)
                .benefits("일상영역 최대 4만P 적립\\n" +
                        "Flex 영역 10% 기본 적립")
                .build();
        cards.add(card3);
        Card card4 = Card.builder()
                .productName("신한카드 The BEST+")
                .companyName("신한카드")
                .thumbnail("https://www.shinhancard.com/pconts/images/contents/card/plate/cdVipALWBPA.png")
                .productURL("https://www.shinhancard.com/pconts/html/card/apply/premium/1188285_2205.html")
                .tags("여행\\n" +
                        "고소득\\n" +
                        "성인")
                .annualFee(300000)
                .benefits("기프트 옵션 연 1회, 택1\\n" +
                        "항공 마일리지 적립\\n" +
                        "공항라운지 무료 이용")
                .build();
        cards.add(card4);
        Card card5 = Card.builder()
                .productName("신한카드 LABE")
                .companyName("신한카드")
                .thumbnail("https://www.shinhancard.com/pconts/images/contents/card/plate/cdVipAL1CI9.png")
                .productURL("https://www.shinhancard.com/pconts/html/card/apply/premium/1202530_2205.html")
                .tags("여행\\n" +
                        "고소득\\n" +
                        "성인")
                .annualFee(200000)
                .benefits("골프 기프트 옵션 연 1회, 택1\\n" +
                        "항공 적립 최대 3 마일리지\\n" +
                        "골핑 or GDR 할인 서비스")
                .build();
        cards.add(card5);
        Card card6 = Card.builder()
                .productName("My First Seduction the Pink")
                .companyName("현대카드")
                .thumbnail("https://www.hyundaicard.com/img/com/card/028879GT_h.png")
                .productURL("https://www.hyundaicard.com/cpc/cr/CPCCR0201_01.hc?cardWcd=TPI")
                .tags("쇼핑\\n" +
                        "고소득")
                .annualFee(150000)
                .benefits("연 최대 50만원 바우처 교환\\n" +
                        "웰컴 바우처 10만원권 제공\\n" +
                        "백화점 상품권(신세계·롯데·현대)으로 교환, 롯데면세점, 호텔에서 사용 가능(사용처 택 1)\\n" +
                        "인천국제공항 라운지 무료 이용\\n" +
                        "무료 발레파킹서비스\\n" +
                        "VISA Signature 서비스, MasterCard World 서비스\\n" +
                        "메탈 플레이트 제공(발급수수료 10만원)")
                .build();
        cards.add(card6);
        Card card7 = Card.builder()
                .productName("에너지플러스카드 Edition2")
                .companyName("현대카드")
                .thumbnail("https://www.hyundaicard.com/img/com/card/card_GCE2_h.png")
                .productURL("https://www.hyundaicard.com/cpc/cr/CPCCR0201_01.hc?cardWcd=GCE2")
                .tags("주유")
                .annualFee(10000)
                .benefits("GS칼텍스 주유 15% / 일반 주유 10% 할인\\n" +
                        "정비, 세차, 주차 5% 할인")
                .build();
        cards.add(card7);
        Card card8 = Card.builder()
                .productName("LOCA Mobility 반띵 카드")
                .companyName("롯데카드")
                .thumbnail("https://vertical.pstatic.net/vertical-cardad/creatives/LO/10227/LO_10227_20230131-123810_ver.png")
                .productURL("https://card-search.naver.com/item?cardAdId=10227")
                .tags("대중교통\\n" +
                        "쇼핑\\n" +
                        "뷰티")
                .annualFee(17000)
                .benefits("버스·지하철 50% 결제일 할인\\n" +
                        "미용실 업종 가맹점 50% 결제일 할인\\n" +
                        "쿠팡 위메프 등 50% 결제일 할인\\n")
                .build();
        cards.add(card8);
        Card card9 = Card.builder()
                .productName("롯데월드카드 (삼성카드)")
                .companyName("삼성카드")
                .thumbnail("https://vertical.pstatic.net/vertical-cardad/creatives/SS/10100/SS_10100_20230103-174044_ver.png")
                .productURL("https://www.samsungcard.com/home/card/cardinfo/PGHPPCCCardCardinfoDetails001?code=AAP1717")
                .tags("학생\\n" +
                        "문화\\n" +
                        "생활\\n" +
                        "청년")
                .annualFee(10000)
                .benefits("롯데월드 입장권 2인 50% 할인\\n" +
                        "온라인서점 7% 결제일 할인\\n" +
                        "배달앱 7% 결제일 할인\\n" +
                        "아파트관리비 7% 결제일 할인")
                .build();
        cards.add(card9);
        Card card10 = Card.builder()
                .productName("하나 국민행복카드")
                .companyName("하나카드")
                .thumbnail("https://vertical.pstatic.net/vertical-cardad/creatives/SK/4056/SK_4056_hor.PNG")
                .productURL("https://card-search.naver.com/item?cardAdId=4056")
                .tags("임신\\n" +
                        "자녀")
                .annualFee(0)
                .benefits("임신, 출산 진료비 지원: 임신 1회당 60만원\\n" +
                        "만 18세 이하 청소년 산모 임신 1회당 120만원 내 지원\\n" +
                        "병원, 약국 업종 5% 청구 할인")
                .build();
        cards.add(card10);

        for (Card card : cards) {
            cardRepository.save(card);
        }

        //Savings
        List<Savings> savings = new ArrayList<>();
        Savings saving1 = Savings.builder()
                .productName("청년내일저축계좌")
                .companyName("하나은행")
                .companyImage("https://upload.wikimedia.org/wikipedia/commons/thumb/a/a5/Hana_Bank_Logo_%28kor%29.svg/2560px-Hana_Bank_Logo_%28kor%29.svg.png")
                .productURL("https://new-m.pay.naver.com/savings/detail/982b3337241d96ba2c9d60ad8ef5f114")
                .basicRate("2.00")
                .primeRate("3.00")
                .aboutPrimeRate("아래의 우대항목을 충족한 경우, 최대 연 3.00%의 우대금리를 만기해지시 제공 (단, 중도해지시 미제공)")
                .qualification("1. 이 예금가입후 만기 전전월말 기준, 본인명의 하나은행 입출금 통장을 통해 12회차 이상 급여입금 (또는 가맹점대금 입금) 및 주거래 이체 실적을 같은 월에 모두 보유한 경우 (월1회 인정)\\n" +
                        "※ 1회차 인정요건 : 급여입금 (또는 가맹점대금 입금) + 주거래이체 : 1.2%\n" +
                        "2. 아래의 ① 또는 ②에 해당하는 경우 \\n" +
                        "① 이 예금 가입일로부터 3개월이 되는 달의 말일기준 당행 ‘주택청약종합저축’을 신규 또는 보유하고 만기 시점까지 유지한 경우\\n" +
                        "② 이 예금 가입일까지 ‘하나원큐(스마트폰뱅킹)’를 통하여 오픈뱅킹서비스를 가입하고, 주택청약종합저축을 등록·조회한 경우 : 1%\\n" +
                        "3. 이 예금 가입전 하나은행 상품·서비스 마케팅 동의 항목 모두를 동의한 경우 : 0.5%\\n" +
                        "4. 이 예금 가입일 기준 익월말까지 『하나 합 서비스』 가입 및 하나은행외 기관을 1개이상 연결하고 만기전전월말에 유지한 경우 : 0.3%")
                .tags("청년\\n" +
                        "학생")
                .build();
        savings.add(saving1);

        Savings saving2 = Savings.builder()
                .productName("스무살 우리 자유 적금")
                .companyName("우리은행")
                .companyImage("https://simg.wooribank.com/img/intro/header/h1_01.png")
                .productURL("https://new-m.pay.naver.com/savings/detail/9dde13efdea27c37aca269be98ca213c")
                .basicRate("2.80")
                .primeRate("3.90")
                .aboutPrimeRate("조건 충족시 최대 연 0.5%p 금리우대")
                .qualification("1. 우리카드(체크/신용) 결제계좌를 우리은행 입출식 계좌로 지정한 후, 실적 인정 기간 동안 월 10만원 이상 결제실적 보유 : 0.3%\\n" +
                        "2. 우리은행 비대면 채널을 통해 이 상품을 가입하는 경우 : 0.2%")
                .tags("청년\\n" +
                        "학생")
                .build();
        savings.add(saving2);

        Savings saving3 = Savings.builder()
                .productName("영플러스(YoungPlus)적금")
                .companyName("DGB대구은행")
                .companyImage("https://s3.us-west-2.amazonaws.com/secure.notion-static.com/0135eb5e-1001-46a7-b26e-117f94c4fe99/20118_12809_1350_%281%29.png?X-Amz-Algorithm=AWS4-HMAC-SHA256&X-Amz-Content-Sha256=UNSIGNED-PAYLOAD&X-Amz-Credential=AKIAT73L2G45EIPT3X45%2F20230213%2Fus-west-2%2Fs3%2Faws4_request&X-Amz-Date=20230213T114759Z&X-Amz-Expires=86400&X-Amz-Signature=6c4b5d7f4b8f01a22e318ab55a82cc67cd183cc27589f1884f087e460d885ccb&X-Amz-SignedHeaders=host&response-content-disposition=filename%3D%2220118_12809_1350%2520%281%29.png%22&x-id=GetObject")
                .productURL("https://new-m.pay.naver.com/savings/detail/8fefa04bfcc81cdefd183749873c8485")
                .basicRate("3.66")
                .primeRate("4.21")
                .aboutPrimeRate("우대이자율 : 최고 연 0.50%p")
                .qualification("1. 신규가입일: 영플러스통장 보유시 연 0.10%p\\n" +
                        "재예치일: 영플러스통장 전월 평잔 10만원 이상인 경우 연 0.10%p : 0.1%\\n" +
                        "2. 예금기간 중 입금횟수가 10회 이상인 경우 : 0.1%\\n" +
                        "3. 초, 중, 고, 대학교 입학 및 대학교 졸업 축하금리 (입학이나 졸업 해당년도 내 영업점창구로 증빙서류 제출 시) : 0.3%\\n" +
                        "4. 인터넷/스마트뱅크에서 가입시 : 0.05%")
                .tags("청년\\n" +
                        "학생")
                .build();
        savings.add(saving3);

        Savings saving4 = Savings.builder()
                .productName("직장인우대예금")
                .companyName("DGB대구은행")
                .companyImage("https://s3.us-west-2.amazonaws.com/secure.notion-static.com/0135eb5e-1001-46a7-b26e-117f94c4fe99/20118_12809_1350_%281%29.png?X-Amz-Algorithm=AWS4-HMAC-SHA256&X-Amz-Content-Sha256=UNSIGNED-PAYLOAD&X-Amz-Credential=AKIAT73L2G45EIPT3X45%2F20230213%2Fus-west-2%2Fs3%2Faws4_request&X-Amz-Date=20230213T114759Z&X-Amz-Expires=86400&X-Amz-Signature=6c4b5d7f4b8f01a22e318ab55a82cc67cd183cc27589f1884f087e460d885ccb&X-Amz-SignedHeaders=host&response-content-disposition=filename%3D%2220118_12809_1350%2520%281%29.png%22&x-id=GetObject")
                .productURL("https://new-m.pay.naver.com/savings/detail/63205b6ac851fae90de75937c7af5e77")
                .basicRate("3.26")
                .primeRate("3.81")
                .aboutPrimeRate("우대이자율 : 최고 연 0.60%p")
                .qualification("1. 인터넷/스마트뱅크에서 가입시 비대면우대이자율 추가 : 0.05%\\n" +
                        "우대이자율 : 최고 연 0.50%p\\n" +
                        "2. 대구은행 수익증권(MMF제외),대출금(지급보증제외)중 하나 보유 고객 : 0.1%\\n" +
                        "3. 지난 3개월 대구은행 계좌를 통한 대구은행 신용카드(체크카드 포함) 결제금액이 30만원 이상인 고객 : 0.1%\n" +
                        "4. 지난 3개월 입출금이자유로운예금(보통, 신자유, 기업자유, 가계당좌, 매일플러스, 매일플러스기업) 예금평균잔액 50만원 이상 : 0.1%\\n" +
                        "5. 지난달 중 1회 이상 또는 지난 3개월 중 2회 이상 대구은행 계좌를 통한 급여이체 실적이 있는 고객 : 0.2%")
                .tags("직장인")
                .build();
        savings.add(saving4);

        Savings saving5 = Savings.builder()
                .productName("IBK평생한가족통장")
                .companyName("IBK기업은행")
                .companyImage("https://blog.kakaocdn.net/dn/tUFqP/btq5QOAMjv3/pQgStk8N7muGHKPj7Ph600/img.png")
                .productURL("https://new-m.pay.naver.com/savings/detail/1b441cfd7fe4796c2eda01915bd0de66")
                .basicRate("3.45")
                .primeRate("3.65")
                .aboutPrimeRate("우대이자율 : 최고 연 0.25%p")
                .qualification("1. 고객별 우대금리 : 최고 연 0.05%p\\n" +
                        "∙ 최초 신규고객 : 0.05\\n" +
                        "※ 가입일 당시 최초 실명등록을 한 고객\\n" +
                        "∙ 재예치 고객 : 0.05\\n" +
                        "※ 상품 출시일 이후 당행 예,적금 만기해지일로부터 1개월 이내에 IBK평생한가족통장(적립식 또는 거치식)을 가입한 고객\\n" +
                        "∙ 장기거래 고객 : 0.05\\n" +
                        "※ 당행에 실명등록한 날로부터 3년이 경과한 고객 : 0.05%")
                .tags("기혼\\n" +
                        "직장인")
                .build();
        savings.add(saving5);

        for (Savings saving : savings) {
            savingsRepository.save(saving);
        }
        //Subscription

        List<Subscription> subscriptions = new ArrayList<>();
        Subscription subscription1 = Subscription.builder()
                .productName("주택청약종합저축")
                .companyName("우리은행")
                .companyImage("https://simg.wooribank.com/img/intro/header/h1_01.png")
                .productURL("https://spot.wooribank.com/pot/Dream?withyou=PODEP0019&cc=c007095:c009166")
                .qualification("무주택자")
                .highRate("2.10")
                .aboutRate("1개월 이내: 무이자\\n" +
                        "1개월 초과 1년 미만: 1.3\\n" +
                        "1년 이상 2년 미만: 1.8\\n" +
                        "2년 이상: 2.1")
                .purchase("매월 2~ 50만원 자유 납입\\n" +
                        "납부 총액이 1,500만원 도달 시 까지는 50만원 초과하여 자유적립 가능")
                .tags("무주택자")
                .build();
        subscriptions.add(subscription1);

        Subscription subscription2 = Subscription.builder()
                .productName("청년 우대형 주택청약종합저축")
                .companyName("우리은행")
                .companyImage("https://simg.wooribank.com/img/intro/header/h1_01.png")
                .productURL("https://spot.wooribank.com/pot/Dream?withyou=PODEP0019&PRD_CD=P010002293&cc=c007095%3Ac009166")
                .qualification("무주택자\\n" +
                        "만 19세 이상~ 만 34세 이하\\n" +
                        "연 소득 3천 6백만원 이하")
                .highRate("3.60")
                .aboutRate("1개월 이내: 무이자\\n" +
                        "1개월 초과 1년 미만: 2.8\\n" +
                        "1년 이상 2년 미만: 3.3\\n" +
                        "2년 이상 10년 이내: 3.6\\n" +
                        "10년 초과: 2.1")
                .purchase("매월 2~ 50만원 자유 납입\\n" +
                        "납부 총액이 1,500만원 도달 시 까지는 50만원 초과하여 자유적립 가능")
                .bound(50000000)
                .tags("무주택자\\n" +
                        "청년")
                .build();
        subscriptions.add(subscription2);

        for (Subscription subscription : subscriptions) {
            subscriptionRepository.save(subscription);
        }
        //Loan

        List<Loan> loans = new ArrayList<>();
        Loan loan1 = Loan.builder()
                .productName("신용대출")
                .companyName("케이뱅크")
                .companyImage("https://upload.wikimedia.org/wikipedia/commons/thumb/2/26/Kbank_logo.svg/2560px-Kbank_logo.svg.png")
                .qualification("직장인")
                .lowRate("6.44")
                .highRate("10.49")
                .bound("연소득의 150% 범위내\\n" +
                        "최대 2억원")
                .productURL("https://search.naver.com/search.naver?where=nexearch&sm=tab_etc&pkid=731&os=29654540&qvt=0&query=%EC%BC%80%EC%9D%B4%EB%B1%85%ED%81%AC%20%EC%8B%A0%EC%9A%A9%EB%8C%80%EC%B6%9C")
                .tags("직장인")
                .build();
        loans.add(loan1);

        Loan loan2 = Loan.builder()
                .productName("모바일프라임론")
                .companyName("광주은행")
                .companyImage("https://mblogthumb-phinf.pstatic.net/MjAyMTAxMjRfMTg2/MDAxNjExNDcyNDQwNjMz.c4nA0jGYMPxo1ZkOCb9DxtC_pMoZJWo14c_VsdXbeuIg.3XJxeZziXV4TUIowr4dAJBJMY0xmVTIchgzH56o_C3sg.PNG.yeosu_bada/%EA%B4%91%EC%A3%BC%EC%9D%80%ED%96%89_%EB%A1%9C%EA%B3%A0_PNG_AI_%EC%9B%90%EB%B3%B8_%EB%8B%A4%EC%9A%B4.png?type=w800")
                .qualification("직장인")
                .lowRate("6.91")
                .highRate("12.71")
                .bound("1백만원 ~ 1억 5천만원")
                .productURL("https://search.naver.com/search.naver?where=nexearch&sm=tab_etc&pkid=731&os=29654553&qvt=0&query=%EA%B4%91%EC%A3%BC%EC%9D%80%ED%96%89%20%EB%AA%A8%EB%B0%94%EC%9D%BC%ED%94%84%EB%9D%BC%EC%9E%84%EB%A1%A0")
                .tags("직장인")
                .build();
        loans.add(loan2);

        Loan loan3 = Loan.builder()
                .productName("사장님대출")
                .companyName("토스뱅크")
                .companyImage("https://s3.us-west-2.amazonaws.com/secure.notion-static.com/4f1d45c8-4451-4cc7-8323-e205dabb325f/IETVG3ZQIBHCDG3KZ3FSNPH7TQ_%281%29.png?X-Amz-Algorithm=AWS4-HMAC-SHA256&X-Amz-Content-Sha256=UNSIGNED-PAYLOAD&X-Amz-Credential=AKIAT73L2G45EIPT3X45%2F20230213%2Fus-west-2%2Fs3%2Faws4_request&X-Amz-Date=20230213T113921Z&X-Amz-Expires=86400&X-Amz-Signature=684480bc7ec0cf52c16b139c9935f2321a84d47889529e8238f16a194903b563&X-Amz-SignedHeaders=host&response-content-disposition=filename%3D%22IETVG3ZQIBHCDG3KZ3FSNPH7TQ%2520%281%29.png%22&x-id=GetObject")
                .qualification("사업자")
                .lowRate("5.99")
                .highRate("15.00")
                .bound("1백만원 ~ 1억원")
                .productURL("https://search.naver.com/search.naver?where=nexearch&sm=tab_etc&pkid=731&os=29654543&qvt=0&query=%ED%86%A0%EC%8A%A4%EB%B1%85%ED%81%AC%20%EC%82%AC%EC%9E%A5%EB%8B%98%EB%8C%80%EC%B6%9C")
                .tags("사업자")
                .build();
        loans.add(loan3);

        Loan loan4 = Loan.builder()
                .productName("KB국민 개인사업자대출")
                .companyName("KB국민카드")
                .companyImage("https://upload.wikimedia.org/wikipedia/commons/thumb/d/d4/KB_logo.svg/2560px-KB_logo.svg.png")
                .qualification("사업자")
                .lowRate("7.40")
                .highRate("19.90")
                .bound("1백만원 ~ 2.5억원")
                .productURL("https://search.naver.com/search.naver?where=nexearch&sm=tab_etc&pkid=731&os=29654552&qvt=0&query=KB%EA%B5%AD%EB%AF%BC%EC%B9%B4%EB%93%9C%20KB%EA%B5%AD%EB%AF%BC%20%EA%B0%9C%EC%9D%B8%EC%82%AC%EC%97%85%EC%9E%90%EB%8C%80%EC%B6%9C")
                .tags("사업자")
                .build();
        loans.add(loan4);

        Loan loan5 = Loan.builder()
                .productName("신용대출플러스")
                .companyName("케이뱅크")
                .companyImage("https://upload.wikimedia.org/wikipedia/commons/thumb/2/26/Kbank_logo.svg/2560px-Kbank_logo.svg.png")
                .qualification("무직\\n" +
                        "직장인")
                .lowRate("7.35")
                .highRate("10.86")
                .bound("최대 1억 5천원")
                .productURL("https://search.naver.com/search.naver?where=nexearch&sm=tab_etc&pkid=731&os=29654541&qvt=0&query=%EC%BC%80%EC%9D%B4%EB%B1%85%ED%81%AC%20%EC%8B%A0%EC%9A%A9%EB%8C%80%EC%B6%9C%ED%94%8C%EB%9F%AC%EC%8A%A4")
                .tags("무직\\n" +
                        "직장인")
                .build();
        loans.add(loan5);

        for (Loan loan : loans) {
            loanRepository.save(loan);
        }

        List<Member> members = new ArrayList<>();
        Member member1 = Member.builder()
                .email("member1@email.com")
                .password(passwordEncoder.encode("q1w2e3r4t5y6"))
                .name("회원1")
                .birthDay(Date.valueOf("1998-03-16"))
                .secession(Scession.OPEN)
                .tags("대학생\\n" +
                        "청년\\n" +
                        "무직")
                .build();
        members.add(member1);
        Member member2 = Member.builder()
                .email("member2@email.com")
                .password(passwordEncoder.encode("q1w2e3r4t5y6"))
                .name("회원2")
                .birthDay(Date.valueOf("1993-12-26"))
                .secession(Scession.CLOSE)
                .tags("직장인\\n" +
                        "청년")
                .build();
        members.add(member2);
        Member member3 = Member.builder()
                .email("member3@email.com")
                .password(passwordEncoder.encode("q1w2e3r4t5y6"))
                .name("회원3")
                .birthDay(Date.valueOf("1988-06-23"))
                .secession(Scession.OPEN)
                .tags("사업자\\n" +
                        "무주택자")
                .build();
        members.add(member3);
        Member member4 = Member.builder()
                .email("member4@email.com")
                .password(passwordEncoder.encode("q1w2e3r4t5y6"))
                .name("회원4")
                .birthDay(Date.valueOf("2002-10-05"))
                .secession(Scession.OPEN)
                .tags("무직\\n" +
                        "쇼핑\\n" +
                        "여행")
                .build();
        members.add(member4);

        for (Member member : members) {
            memberRepository.save(member);
        }
    }

}
