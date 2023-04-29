from selenium import webdriver
from selenium.webdriver.common.by import By

# URL of the theater page
#CGV_THEATER_URL = 'http://www.cgv.co.kr/theaters/?areacode=01&theaterCode=0013'     # CGV 용산아이파크몰
CGV_THEATER_URL = 'http://www.cgv.co.kr/theaters/?areacode=02&theaterCode=0004&date=20230429'

option = webdriver.ChromeOptions()
option.add_experimental_option("useAutomationExtension", False)
option.add_experimental_option("excludeSwitches", ['enable-automation'])
driver = webdriver.Chrome(executable_path='chromedriver', options=option)
driver.delete_all_cookies()

driver.get(url=CGV_THEATER_URL)
innerIframe = driver.find_element(By.ID, "ifrm_movie_time_table")
driver.switch_to.frame(innerIframe)
#a = driver.find_element(By.CLASS_NAME, "showtimes-wrap").find_elements(By.CLASS_NAME, "info-movie")
a = driver.find_element(By.CLASS_NAME, "showtimes-wrap").find_elements(By.CLASS_NAME, "type-hall")
#print(a)
for i in a:
    print(i.text)
    b = i.find_elements(By.CLASS_NAME, "info-timetable")
    #print(b)
    for j in b:
         print(j.text)



#wrap = driver.find_element(By.CLASS_NAME, "wrap")
# innerIframe = driver.find_element(By.ID, "ifrm_movie_time_table")
# driver.switch_to.frame(innerIframe)

# time_table = driver.find_element(By.CSS_SELECTOR, "#slider").find_elements(By.CLASS_NAME, "item-wrap")
# last_time = time_table[0].find_elements(By.CLASS_NAME, 'day')[0]
# last_month = int(last_time.find_element(By.TAG_NAME, 'span').get_attribute('innerText').strip()[:-1])
# last_day = int(last_time.find_element(By.TAG_NAME, 'strong').get_attribute('innerText').strip())

#print(f'마지막으로 예약 가능한 날짜는 {last_month}월 {last_day}일 입니다.')

print()