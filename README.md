
       Система управление деятельностью школы.

       Краткое описание:
       Разработка веб-приложения для автоматизации основных процессов управления деятельностью школы:
       расписание уроков, учёт успеваемости и посещаемости, общение между участниками образовательного процесса,
       формирование отчётов и др.

       Цель проекта:
       Упрощение и автоматизация рутинных задач, выполняемых администрацией школы, учителями и классными руководителями.
       Повышение эффективности коммуникации между школой, учениками и родителями.
       Обеспечение прозрачности учебного процесса и доступа к информации об успеваемости и посещаемости.


       Пользователи системы:
                            Администратор системы
                            Директор школы
                            Секретарь
                            Завуч
                            Классные руководители
                            Учителя-предметники
                            Ученики
                            Родители

      Функциональные модули:
                 Авторизация и управление пользователями:
                          Регистрация и аутентификация пользователей с различными ролями.
                          Возможность добавления, редактирования и удаления пользователей (для администратора).
                          Управление профилями пользователей.

                 Управление учебным процессом:
                          Формирование и редактирование учебных планов (предметы, классы).
                          Создание и редактирование расписания уроков и замен.
                          Ведение электронного журнала (учет посещаемости, выставление оценок).
                          Формирование домашних заданий.

                 Коммуникация:
                          Обмен сообщениями между пользователями (учитель-ученик, учитель-родитель, родитель-родитель).
                          Создание и публикация объявлений и новостей.
                          Возможность проведения онлайн-консультаций (опционально).

                 Отчетность:
                          Формирование отчетов об успеваемости учеников (по четвертям, годам).
                          Отчеты о посещаемости.
                          Другие виды отчетов по требованию.

                 Этапы разработки:
                          Проектирование базы данных.
                          Разработка REST API на Spring Boot.
                          Тестирование и отладка.
                          Документирование.

                  Результат работы
                          Исходный код приложения.
                          Документация (описание API, руководство пользователя).
                          Презентация проекта.


    Минимальный функционал приложения:

         1. Администратор системы:
            Отвечает за техническое функционирование системы.
            Создает и удаляет учетные записи пользователей (всех ролей).
            Назначает роли пользователям.
            Управляет правами доступа к разделам системы.
            Мониторит производительность и безопасность системы. (Не обязательный пункт)
            Выполняет резервное копирование и восстановление данных. (Не обязательный пункт)
                1) Добавление пользователя в систему:
                2) Редактирование данных пользователя и его ролей:
                3) Удаление пользователя из системы:

         2. Директор:
            Обладает полным доступом к информации в системе.
            Утверждает расписание уроков и замен.
            Просматривает успеваемость и посещаемость учеников.
            Формирует отчеты по различным показателям (успеваемость, посещаемость, финансы и т.д.).
            Управляет штатным расписанием и заработной платой учителей (возможно, делегирует секретарю).
            Взаимодействует с родителями через систему (объявления, сообщения).
                1) Добавление редактирование и увольнение работников
                2) Добавление редактирование предметов школы
                3) Просмотр списка преподователей
                4) Формирование отчетов
                5) Издание указов для школы

         3. Секретарь Директора:
            Выполняет поручения директора.
            Ведет делопроизводство (приказы, распоряжения).
            Готовит отчеты по запросу директора.
            Работает с обращениями родителей и учеников.
               1) Просмотр поручений от Директора
               2) Исполнение поручений Директора
               3) Просмотр списка заявок на поступение новых детей в школу

         4. Завуч:
            Формирует расписание уроков и замен.
            Распределяет учебную нагрузку между учителями.
            Контролирует успеваемость и посещаемость учеников.
            Работает с классными руководителями по вопросам успеваемости и дисциплины.
                1) Добавление, Удаление, Редактирование и Просмотр классов
                2) Добавление, Удаление, Редактирование и Просмотр придметов в классе
                3) Просмотр, Редактирование и Исключение школьника из класса
                 4) Формирование отчета
                5) Издание указов для классов

         5. Классный руководитель:
            Ведет журнал класса (отмечает посещаемость, выставляет оценки).
            Просматривает успеваемость учеников своего класса.
            Взаимодействует с родителями учеников (родительские собрания, отчеты об успеваемости, сообщения).
            Организует внеклассные мероприятия.
              1) Просмотр поручений от Завуча
              2) Исполнение поручение Завуча
              3) Назначение классного старасту
              4) Просмотр списка учеников в классе за который ответственнен класс-рук
              5) Просмотр списка отзывово об ученике от других преподователей
              6) Формирование Автобиографии ученика
              7) Создание поручений для старосты
              8) Просмотр Списка выполненых поручений от старосты
              9) Просмотр Отзывово об учениках сформированных старостой

         6. Преподаватель:
            Просматривает расписание своих уроков.
            Ведет электронный журнал (отмечает посещаемость, выставляет оценки, оставляет домашние задания).
            Просматривает успеваемость учеников по своему предмету.
            Общается с родителями учеников через систему (сообщения, консультации).
              1) Просмотр списка классов
              2) Просмотр списка учеников в классе
              3) Просмотр успеваемости учеников в классе (По предмету который преподает)
              4) Формирование отчетов
              5) Составление отзыва о студенте
              6) Формирования домашнего задания
              7) Просмотр списка выполненых работ по домашнему заданию и вытавление оценки
              8) Составление отзыва по решонной домашней работе для ученика

         7. Староста класса:
            Староста получает уведомление от классного руководителя
            с просьбой написать отзыв об однокласснике (например,  для характеристики, портфолио).
            Пишет отзыв, описывая личные качества, учебные достижения, участие в жизни класса и школы.
            Староста регулярно проверяет список распоряжений от классного руководителя в системе
            (например, "собрать подписи родителей", "напомнить про мероприятие").
            Просматривать списка учебных дней и составлять список дежурных на данный день
                     1) Составление отзыва об однокласснике (Для старосты)
                     2) Выполнение распоряжений от классного руководителя
                     4) Просмотр списка поручений от классного руководителя
                     5) Составление графика дежурств по классу

         8. Школьник:
            Просматривает расписание уроков, домашних заданий и оценок.
            Получает доступ к учебным материалам.
            Общается с учителями и одноклассниками через систему (сообщения, форумы).
               1) Просмотр списка своих предметов
               2) Просмотр списка учеников в классе
               3) Просмотр списка тем по предмету
               4) Просмотр списка уроков
               5) Просмотр списка домашних работ по уроку
               6) Отправка выполненой домашней работы
               7) Просмотр списка оценок
               8) Получение расписания

         9. Родитель:
            Просматривает расписание уроков,  домашние задания, оценки и  посещаемость своего ребенка.
            Получает доступ к информации о школьных мероприятиях и объявлениях.
            Общается с учителями и администрацией школы через систему (сообщения, запись на консультации).
                1) Регистрация ребенка на обучение в Школе
                2) Получение расписания
                3) Получение списка заданий по предмету
                4) Просмотр успеваемости ребенка
                5) Просмотр отзывов от учетелей
                6) Отчисление по собственному желанию


    Примерная структура базы данных:

    1. Пользователи:
       Хранит информацию о всех, кто использует систему:
       директор, учителя, ученики, родители, администратор.
       У каждого пользователя есть имя, фамилия, электронная почта, пароль и роль, определяющая, к каким функциям у него есть доступ.

    2. Сотрудники:
       В этом ящике хранятся данные о тех, кто работает в школе:
       ФИО, должность, зарплата.
       Каждый сотрудник связан с пользователем (чтобы он мог войти в систему).

    3. Предметы:
       Здесь хранится информация о предметах, которые преподают в школе:
       название и краткое описание.

    4. Классы:
       В этой таблице хранятся данные о каждом классе:
       номер класса и кто является классным руководителем (связь с таблицей "Сотрудники").

    5. Ученики:
       Содержит информацию об учениках:
       ФИО,  в каком классе учатся (связь с таблицей "Классы").
       Каждый ученик также связан с пользователем (для входа в систему).

    6. Родители:
       Здесь хранятся данные о родителях:
       ФИО,  и связь с учеником (чтобы родители могли видеть информацию о своем ребенке).
       Каждый родитель также связан с пользователем.

    7. Связь учителей и предметов:
       В этой таблице мы указываем, какие предметы преподает каждый учитель.
       Учитель может вести несколько предметов, а один предмет могут вести несколько учителей.

    8. Объявления:
       Здесь хранятся важные сообщения для всей школы:
       заголовок,  текст объявления,  автор (кто опубликовал) и дата публикации.

    9. Посещаемость:
       В этой таблице отмечается, кто из учеников присутствовал на уроках.

    10. Оценки:
        Здесь хранятся оценки учеников по разным предметам.


"Один ко многим": один пользователь может быть связан с несколькими записями в другой таблице. Например:
    Один пользователь может быть родителем нескольких учеников.
    Один учитель может вести несколько предметов.
"Многие ко многим": одна запись в таблице может быть связана с несколькими записями в другой таблице, и наоборот. Например:
    Ученик может получать оценки по разным предметам у разных учителей.
