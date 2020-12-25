FROM postgres:13.1-alpine

ENV POSTGRES_PASSWORD root
ENV POSTGRES_DB openmind_db

#RUN echo "listen_addresses='*'" >> /var/lib/postgres/data/postgresql.conf
#
#CMD su postgres -c 'pg_ctl -D /var/lib/postgresql/data -l logfile start'
