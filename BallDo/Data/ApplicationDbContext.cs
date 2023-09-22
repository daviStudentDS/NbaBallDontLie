using Microsoft.EntityFrameworkCore;
using BallDo.Models;

namespace BallDo.Data
{
    public class ApplicationDbContext : DbContext
    {
        public ApplicationDbContext(DbContextOptions<ApplicationDbContext> options)
            : base(options) { }

        public DbSet<Coach> Coaches { get; set; }
        public DbSet<Player> Players { get; set; }
        public DbSet<Team> Teams { get; set; }

        protected override void OnModelCreating(ModelBuilder modelBuilder)
        {
            modelBuilder.Entity<Player>()
                .HasOne(a => a.Team)
                .WithMany()
                .HasForeignKey(a => a.TeamId);

            modelBuilder.Entity<Coach>()
                .HasOne(a => a.Team)
                .WithMany()
                .HasForeignKey(a => a.TeamId);
        }
    }
}
    